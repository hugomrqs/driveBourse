package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.*;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
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
                    .log("Je suis un fond interessé et j'ai récupérer le OnePager : ${in.body}")
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
                .to("sjms2:topic:" + jmsPrefix + "sender") ;

        from("sjms2:topic:" + jmsPrefix + "businessPlanForFond" + helper.siret)
                .autoStartup(isRouteEnabled)
                .log("le BusinessPlan envoyé par Tasvee a été reçu")
                .unmarshal().json(BusinessPlanDTO.class)
                .bean(businessService, "createPropfromBP").marshal().json();



        from("sjms2:topic:" + jmsPrefix + "proposalForFond")
                .autoStartup(isRouteEnabled)
                .log("Proposition  de Tasvee  reçu")
                .choice()
                .when(simple("${header.etatProp} == true"))
                .log("La proposition de Tasvee a été reçu et est accepté")
                .bean(propositionService, "addLastProposal")
                .when(simple("${header.etatProp} == false"))
                .log("La proposition de Tasvee a été reçu et est refusé")
                .unmarshal().json(PropositionDTO.class)
                .bean(propositionService, "challengeProposal").marshal().json()
                .end();


        from("direct:sendProposal")
                .autoStartup(isRouteEnabled)
                .log("proposition envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "proposalForTasvee");


        from("sjms2:topic:" + jmsPrefix + "NDACommercialForFond")
                .autoStartup(isRouteEnabled)
                .log("Le contrat a été reçu par Fond")
                .marshal().json(NDADTOCommercialisationDTO.class)
                .bean(paymentService, "signNDACom").marshal().json();


        from("direct:signedNDA")
                .autoStartup(isRouteEnabled)
                .log("Le contrat signé par Fond est envoyé à Tasvee")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "signedNDAForTasvee");

        from("sjms2:topic:" + jmsPrefix + "ribOfEntrepreneur")
                .autoStartup(isRouteEnabled)
                .log("Le RIB de Entrepreneur a été récupérer")
                .unmarshal().json(RIBDTO.class)
                .bean(paymentService, "sendMoneyToEntrepreneur")
                .marshal().json();

        from("sjms2:topic:" + jmsPrefix + "ribOfTasvee")
                .autoStartup(isRouteEnabled)
                .log("Le RIB de Tasvee a été récupérer")
                .unmarshal().json(RIBDTO.class)
                .bean(paymentService, "sendMoneyToTasvee")
                .marshal().json();

    }
}