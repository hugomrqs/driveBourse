package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessModelService;
import jakarta.enterprise.context.ApplicationScoped;
import fr.pantheonsorbonne.ufr27.miage.service.ContratService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
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

    @Inject
    @Named("businessModelEntrepriseService")
    BusinessModelService bm;

    @Inject
    ContratService contratService;

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


        from("file:data/BM")
                .log("bm recu ${body}")
                .unmarshal().json(BusinessModel.class)
                .bean(bm,"registerBusinessModel")
                .marshal().json();

        /////////////////////
        ////Gestion du contrat juridique
        /////////////////////

        from("file:data/CJ")
                .log("cjbm recu ${body}")
                .unmarshal().json(ContratJuridiqueBM.class)
                .bean(bm,"registerContratJuridiqueBM")
                .marshal().json();


        from("direct:startup-smtp")
                  .autoStartup(isRouteEnabled)
                .log("///////////////////////")
                .marshal().json()
                .log("${body}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("contentType", "application/json");
                        exchange.getMessage().setHeader("subject", "Send signé");
                    }
                })
                .to("sjms2:topic:" + jmsPrefix + "sender");


        from("sjms2:topic:" + jmsPrefix + "NDACommercialForEntrepreneur")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .unmarshal().json(NDADTOCommercialisationDTO.class)
                .bean(contratService, "signNDA").marshal().json();

        from("direct:sendNDA")
                .autoStartup(isRouteEnabled)
                .log("nda sign for tasvee")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "signedNDAForTasvee");

    }
}