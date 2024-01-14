package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOProductionDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.service.ContratJuridiqueOnePagerPourBPService;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerInteretService;
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
    FundsInterestedGateway fundsInterestedGateway;
    @Inject
    ContratJuridiqueOnePagerPourBPService contratJuridiqueOnePagerPourBPService;

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

//        from("sjms2:topic:"+ jmsPrefix +"T") route que fond utilise
//                .log("OnePager: ${in.headers} ${in.body}");

        from("sjms2:" + jmsPrefix + "queue:interestedIn")
                .filter(header("IsInterested").isEqualTo(true))
                .unmarshal().json(OnePagerInteret.class)
                .aggregate(header("idOnePager"), new InteretAgregationStrategy())
                .completionSize(2)
                .completionTimeout(10000)
                .log("voici la liste des fonds intéressés par l'offre : ${in.body}")
                .to("direct:processInteretOnePager");

        from("direct:processInteretOnePager")
                .split(body())
                .log("pour l'interet : ${in.body}")
                .bean(contratJuridiqueOnePagerPourBPService, "createContratJuridiqueOnePagerPourBP(${in.body}")
                .log("le contrat numero : ${in.body} a bien été crée")
                .bean(contratJuridiqueOnePagerPourBPService, "SendContratJuridiqueOnePagerPourBP(${$in.body}")
                .log("le contrat numero : ${in.body} a bien été envoyé")
                .end();

        from("sjms2:" + jmsPrefix + "queue:ContratJuridiqueOnePagerPourBP")//ici file/pdf
                .unmarshal().json(NDADTOProductionDTO.class)
                .bean(contratJuridiqueOnePagerPourBPService, "UpdateContratJuridiqueOnePagerPourBPSigne(${in.body})")
                .log("le contrat : ${in.body} signé a bien été reçu et enregistré");
    }
}
