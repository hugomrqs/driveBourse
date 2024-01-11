package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Interet;
import fr.pantheonsorbonne.ufr27.miage.service.InteretService;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

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


    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.password")
    String smtpPassword;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.host")
    String smtpHost;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.port")
    String smtpPort;


    @Inject
    CamelContext camelContext;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        from("direct:smtp")
                .log("${headers}")
                .choice()
                .when(header("subject").isEqualTo("BM"))
                .unmarshal().json(BusinessModelDTO.class)
                .to("file:data")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "-SMTP");


        from("sjms2:topic:" + jmsPrefix + "-SMTP")
                .autoStartup(isRouteEnabled)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // Créer un nouveau document PDF
//                        PDDocument document = new PDDocument();
//                        PDPage page = new PDPage();
//                        document.addPage(page);
//
//                        // Ajouter du contenu au PDF
//                        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//                        contentStream.beginText();
//                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//                        contentStream.showText("Contenu du PDF");
//                        contentStream.endText();
//                        contentStream.close();
//
//                        // Convertir le PDF en byte array
//                        ByteArrayOutputStream out = new ByteArrayOutputStream();
//                        document.save(out);
//                        document.close();
//
//                        // Définir le byte array comme corps du message
//                        exchange.getMessage().setBody(out.toByteArray());

                        // Définir les en-têtes
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("contentType", "text/html");
                        exchange.getMessage().setHeader("subject", "Business Model");
                        exchange.getMessage().setBody("Bonjour,\n\n Suite à votre prise de contact sur notre site via l'offerForm " +
                                "\n\n Tasvee à le plaisir de vous annocer notre collaboration" +
                                " \n\n Veuillez trouver ci joint votre Business plan ");
                    }
                })
                .log("${headers}")
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");

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
