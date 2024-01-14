package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerInteretService;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;



@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;
    @Inject
    OnePagerInteretService onePagerInteretService;
    @Inject
    PropositionService propositionService;
    @Inject
    FundsInterestedGateway fundsInterestedGateway;

    @Override
    public void configure() throws Exception {

        from("direct:OnePager")
                .autoStartup(isRouteEnabled)
                .log("OnePager : Secteur = ${in.headers}")
                .marshal().json()
                .choice()
                    .when(header("Secteur").in("T", "S", "I", "F", "E"))
                        .toD("sjms2:topic:" + jmsPrefix + "${in.headers.Secteur}")
                        .log("sjms2:topic:" + jmsPrefix + "${in.headers.Secteur}")
                    .otherwise()
                        .log("Domaine non pris en charge");

//        from("sjms2:topic:"+ jmsPrefix +"Tech") route que fond utilise
//                .log("OnePager: ${in.headers} ${in.body}");

        from("sjms2:" + jmsPrefix + "queue:interestedIn")
                .filter(header("IsInterested").isEqualTo(true))
                .unmarshal().json(OnePagerInteret.class)
                .aggregate(header("idOnePager"), new InteretAgregationStrategy())
                .completionSize(2)
                .completionTimeout(10000)
                .to("direct:processInteretOnePager");

//  @TODO      from("direct:processInteretOnePager")
//                // Créer le contrat en base de données
//                .bean(contratJuridiqueService, "createContratJuridiqueOnePagerPourBPDatabase")
//                // Créer le DTO
//               contratJuridiqueService, "createContratJuridiqueOnePagerPourBPDTO")
//                // Envelopper le DTO dans un message Camel et préparer pour l'envoi
//                .bean(ContratJuridiqueGateway, "sendContratJuridiqueOnePagerPourBP()");
//                "contratJuridiqueService,"createContratJuridiqueOnePagerPourBPDTO\""


        from("sjms2:topic:" + jmsPrefix + "proposalForTasvee")
                .autoStartup(isRouteEnabled)
                .log("Proposition de Fond reçu")
                .choice()
                .when(simple("${header.etatProp} == 'Accepter'"))
                .log("Proposition de Fond accepté")
                .bean(propositionService, "addLastProposal")
                .when(simple("${header.etatProp} == 'Refuser'"))
                .log("Proposition de Fond refusé")
                .unmarshal().json(PropositionDTO.class)
                .bean(propositionService, "challengeProposal").marshal().json()
                .end();

        from("direct:sendProposal")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "proposalForFond");

        from("direct:sendContratTrip")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "NDACommercialForFond");

        from("sjms2:topic:" + jmsPrefix + "signedNDAForTasvee")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "NDACommercialForEntrepreneur");


        from("sjms2:topic:" + jmsPrefix + "signedNDAForTasvee")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .bean(propositionService, "insertNDA").marshal().json();

        from("direct:ribOfTasvee")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "ribOfTasvee");

        from("direct:ribOfEntrepereneur")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "ribOfEntrepereneur");



    }
}
