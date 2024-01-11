package fr.pantheonsorbonne.ufr27.miage.camel;

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
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

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

    @Override
    public void configure() throws Exception {


        from("sjms2:topic:" + jmsPrefix + "proposalForFond")
                .autoStartup(isRouteEnabled)
                .log("Proposition reçu par Tasvee")
                .choice()
                .when(simple("${header.etatProp} == 'Accepter'"))
                .log("Proposition accepté par Tasveee")
                .bean(propositionService, "addLastProposal")
                .when(simple("${header.etatProp} == 'Refuser'"))
                .log("Proposition refuser par Tasvee")
                .unmarshal().json(PropositionDTO.class)
                .bean(propositionService, "challengeProposal").marshal().json()
                .end();


        from("sjms2:topic:" + jmsPrefix + "businessPlanForFond")
                .autoStartup(isRouteEnabled)
                .log("BusinessPlan reçu par Tasvee")
                .unmarshal().json(BusinessPlanDTO.class)
                .bean(businessService, "createPropfromBP").marshal().json();


        from("direct:sendProposal")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "proposalForTasvee");


        from("direct:proposalAccepted")
                .autoStartup(isRouteEnabled)
                .log("proposal accepted by Fond")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "proposalForTasvee");


        from("direct:signedNDA")
                .autoStartup(isRouteEnabled)
                .log("NDA commercialisation signed")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "signedNDA");


        from("sjms2:topic:" + jmsPrefix + "NDACommercialForFond")
                .autoStartup(isRouteEnabled)
                .log("NDA commercialisation reçu par Fond")
                .marshal().json(NDADTOCommercialisationDTO.class)
                .bean(paymentService, "signNDACom").marshal().json();


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


        from("direct:testEnvoiBP")
                .autoStartup(isRouteEnabled)
                .log("LE BUISNESS PLAN")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "businessPlanForFond");



    }
}