package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
import fr.pantheonsorbonne.ufr27.miage.service.TraitementOnePagerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;
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
    }
}