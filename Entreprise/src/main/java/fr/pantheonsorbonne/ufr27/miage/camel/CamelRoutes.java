package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessModelService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;

public class CamelRoutes extends RouteBuilder {

    BusinessModelService reply;
    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.imap.host")
    String imapHost;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.imap.port")
    String imapPort;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.password")
    String smtpPassword;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.host")
    String smtpHost;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.port")
    String smtpPort;


    @Override
    public void configure() throws Exception {

        /////////////////////
        //// endpoint si pas de imap
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "-StartUp-SMTP")
                .autoStartup(isRouteEnabled)
                .choice()
                //oriente selon le header
                .when(header("subject").in("BM", "CJ", "CJOPBP"))
                .toD( "sjms2:topic:"+ jmsPrefix + "-StartUp-${in.headers.subject}");


        /////////////////////
        ////Gestion du BM
        /////////////////////


                from("sjms2:topic:"+jmsPrefix+"-StartUp-BM")
                .unmarshal().json(BusinessModelEntity.class)
                .bean(reply,"registerBusinessModel")
                .marshal().json();

        /////////////////////
        ////Gestion du contrat juridique
        /////////////////////

                from("sjms2:topic:"+jmsPrefix+"-StartUp-CJ")
                .unmarshal().json(ContratJuridiqueBMEntity.class)
                .bean(reply,"registerContratJuridiqueBM ")
                .marshal().json()
                        .to("sjms2:topic:"+jmsPrefix+"-SMTP");

        /////////////////////
        ////Gestion du contrat juridiqueOPBP
        /////////////////////

        from("sjms2:topic:"+jmsPrefix+"-StartUp-CJOPBP")
                //.unmarshal().json(BusinessModelDTO.class)
                .bean(reply,"xxxxx")
                .marshal().json();


        from("direct:startup-smtp")
                .marshal().json()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        BusinessModelDTO notice = exchange.getMessage().getBody(BusinessModelDTO.class);
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("contentType", "text/html");
                        exchange.getMessage().setHeader("subject", "Send BM");
                        exchange.getMessage().setBody("Cher(e) Client(e)," +
                                "\n\n L'équipe Tasvee");
                    }
                })
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword);
    }
}
