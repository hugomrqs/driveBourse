package fr.pantheonsorbonne.ufr27.miage.java.camel;

import fr.pantheonsorbonne.ufr27.miage.java.DAO.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.java.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.java.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.java.service.ReplyService;
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
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;


    @Inject
    CamelContext camelContext;

    ReplyService reply;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);


        from("sjms2:topic:" + jmsPrefix + "reply")
                .autoStartup(isRouteEnabled)
                .log("BusinessPlan reçu par Tasvee")
                //unmarshal().json()
                .unmarshal().json(BusinessModel.class)
                .bean(reply, "addBm").marshal().json();

        from("sjms2:topic:" + jmsPrefix + "reply")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("contentType", "application/pdf");
                        exchange.getMessage().setHeader("subject", "Contrat Juridique");
                    }
                })
                //processor pour signer le contrat
                .to("smtps://smtp.univ-paris1.fr:465?username=hugo.marques@etu.univ-paris1.fr&password=MIAGETasvee12");
    }
}