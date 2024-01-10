package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Interet;
import fr.pantheonsorbonne.ufr27.miage.service.InteretService;
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
    InteretService interetService;
    @Inject
    FundsInterestedGateway fundsInterestedGateway;

    @Override
    public void configure() throws Exception {

        from("direct:OnePager")
                .autoStartup(isRouteEnabled)
                .log("OnePager : Secteur = ${in.headers}")
                .marshal().json()
                .choice()
                    .when(header("Secteur").in("Tech", "b", "c", "d", "e"))
                        .toD("sjms2:topic:" + jmsPrefix + "${in.headers.Secteur}")
                        .log("sjms2:topic:" + jmsPrefix + "${in.headers.Secteur}")
                    .otherwise()
                        .log("Domaine non pris en charge");

        from("sjms2:topic:"+ jmsPrefix +"Tech")
                .log("Received message on Topic: " + jmsPrefix + "${in.headers.Secteur}")
                .log("Message Content: ${body}");

        from("sjms2:" + jmsPrefix + "queue:interestedIn")
                .unmarshal().json(Interet.class)
                .aggregate(header("idOnePager"), new InteretAgregationStrategy())
                .completionSize(2)
                .completionTimeout(10000)
                .to("direct:processInteretOnePager");

//        from("direct:processInteretOnePager")
//                .bean(interetService, "traiterReponses");

    }
}
