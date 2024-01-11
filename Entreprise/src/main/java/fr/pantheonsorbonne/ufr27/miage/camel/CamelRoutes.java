package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessModelService;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

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

        from("sjms2:topic:" + jmsPrefix + "-SMTP2")
                .autoStartup(isRouteEnabled)
                .choice()
                //oriente selon le header
                .when(header("subject").in("BM", "CJ", "CJOPBP"))
                .to( "sjms2:topic:"+ jmsPrefix + "${in.headers.Secteur}");

                from("sjms2:topic:"+jmsPrefix+"BM")
                .unmarshal().json(BusinessModelEntity.class)
                .bean(reply,"registerBusinessModel")
                .marshal().json();

                from("sjms2:topic:"+jmsPrefix+"CJ")
                .unmarshal().json(ContratJuridiqueBMDTO.class)
                .bean(reply,"registerContratJuridiqueBM ")
                .marshal().json()
                        .to("sjms2:topic:"+jmsPrefix+"-SMTP");

        from("sjms2:topic:"+jmsPrefix+"CJOPBP")
                //.unmarshal().json(BusinessModelDTO.class)
                .bean(reply,"addBM")
                .marshal().json();
        ;

    }
}
