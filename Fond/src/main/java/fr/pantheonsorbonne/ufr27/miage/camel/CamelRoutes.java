package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.*;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBPEntity;
import fr.pantheonsorbonne.ufr27.miage.service.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String  jmsPrefix;

    @Inject
    SMTPGateway smtp;

    @Inject
    MessagingGateway msging;

    @Inject
    BusinessService businessService;

    @Inject
    PropositionService propositionService;

    @Inject
    PaymentService paymentService;
    @Inject
    CamelContext camelContext;
    @Inject
    ResponseOnePagerGateway responseOnePagerGateway;
    @Inject
    TraitementOnePagerService traitementOnePagerService;
    @Inject
    Helper helper;
    @Inject
    ContratJuridiqueOnePagerPourBPService contratJuridiqueOnePagerPourBP;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.password")
    String smtpPassword;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.host")
    String smtpHost;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.port")
    String smtpPort;

    @Override
    public void configure() throws Exception {

        //ecouter les topics contenus dans le Helper de paramétrage
        for (String topic : helper.topicsToListen) {
            from("sjms2:topic:" + jmsPrefix + topic)
                    .log("${in.body}")
                    .unmarshal().json(OnePagerDTO.class)
                    .bean(responseOnePagerGateway, "SendResponse(${in.body},${in.headers.ReplyTo})");
        }
        from("direct:cool")
                .log("${in.body}") //test
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "queue:interestedIn");

        from("file:ContratJuridiqueOnePagerPourBP")
                .unmarshal().json(NDADTOProductionDTO.class)
                .bean(contratJuridiqueOnePagerPourBP, "ContratJuridiqueOnePagerPourBPServiceImpl")
                .marshal().json()
                .log("Le NDA ${in.body} va être envoyé")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("contentType", "application/JSON");
                        exchange.getMessage().setHeader("subject", "ContratJuridiqueOnePagerPourBP");
                    }
                })
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword);//@TODO broker smtp


        from("sjms2:topic:" + jmsPrefix + "businessPlanForFond" + helper.siret)
                .autoStartup(isRouteEnabled)
                .log("BusinessPlan de Tasvee  reçu")
                .unmarshal().json(BusinessPlanDTO.class)
                .bean(businessService, "createPropfromBP").marshal().json();


        from("sjms2:topic:" + jmsPrefix + "proposalForFond")
                .autoStartup(isRouteEnabled)
                .log("Proposition  de Tasvee  reçu")
                .choice()
                .when(simple("${header.etatProp} == 'Accepter'"))
                .log("Proposition  de Tasvee  reçu accepté")
                .bean(propositionService, "addLastProposal")
                .when(simple("${header.etatProp} == 'Refuser'"))
                .log("Proposition de Tasvee  reçu refusé")
                .unmarshal().json(PropositionDTO.class)
                .bean(propositionService, "challengeProposal").marshal().json()
                .end();


        from("direct:sendProposal")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "proposalForTasvee");


        from("sjms2:topic:" + jmsPrefix + "NDACommercialForFond")
                .autoStartup(isRouteEnabled)
                .log("NDA commercialisation reçu par Fond")
                .marshal().json(NDADTOCommercialisationDTO.class)
                .bean(paymentService, "signNDACom").marshal().json();


        from("direct:signedNDA")
                .autoStartup(isRouteEnabled)
                .log("NDA commercialisation signed")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "signedNDAForTasvee");

        from("sjms2:topic:" + jmsPrefix + "ribOfEntrepreneur")
                .autoStartup(isRouteEnabled)
                .log("rib de entrepreneur recupérer")
                .unmarshal().json(RIBDTO.class)
                .process(exchange -> {
                    RIBDTO ribDto = exchange.getIn().getBody(RIBDTO.class);
                    exchange.setProperty("ribDto", ribDto);
                })
                .bean(paymentService, "sendMoney(${exchangeProperty.ribDto}, 'Entrepreneur')")
                .marshal().json();

        from("sjms2:topic:" + jmsPrefix + "ribOfTasvee")
                .autoStartup(isRouteEnabled)
                .log("rib de Tasvee recupérer")
                .unmarshal().json(RIBDTO.class)
                .process(exchange -> {
                    RIBDTO ribDto = exchange.getIn().getBody(RIBDTO.class);
                    exchange.setProperty("ribDto", ribDto);
                })
                .bean(paymentService, "sendMoney(${exchangeProperty.ribDto}, 'Tasvee')")
                .marshal().json();

        from("direct:moneyForTasvee")
                .autoStartup(isRouteEnabled)
                .log("argent envoyé pour Tasvee")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "moneyForTasvee");


        from("direct:moneyForEntrepreneur")
                .autoStartup(isRouteEnabled)
                .log("argent envoyé pour Entrepreneur")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "moneyForEntrepreneur");




    }
}