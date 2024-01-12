package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
import fr.pantheonsorbonne.ufr27.miage.service.TraitementOnePagerService;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.RIBDTO;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessService;
import fr.pantheonsorbonne.ufr27.miage.service.PaymentService;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.eclipse.microprofile.config.inject.ConfigProperty;

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
    Helper helper;
    @Override
    public void configure() throws Exception {
        //ecouter les topics contenus dans le Helper de paramétrage
        for (String topic : helper.topicsToListen) {
            from("sjms2:topic:" + jmsPrefix + topic)
                    .unmarshal().json(OnePager.class)
                    .bean(responseOnePagerGateway, "SendResponse(${body},${in.headers.ReplyTo})");
        }



        from("sjms2:topic:" + jmsPrefix + "businessPlanForFond")
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


        from("sjms2:topic:" + jmsPrefix + "ribOfTasvee")
                .autoStartup(isRouteEnabled)
                .log("rib de tasvee recupérer")
                .marshal().json(RIBDTO.class)
                .bean(paymentService, "sendMoney(,Tasvee)").marshal().json();


        from("sjms2:topic:" + jmsPrefix + "ribOfEntrepreneur")
                .autoStartup(isRouteEnabled)
                .log("rib de entrepreneur recupérer")
                .marshal().json(RIBDTO.class)
                .bean(paymentService, "sendMoney(,Entrepreneur)").marshal().json();


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