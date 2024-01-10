package fr.pantheonsorbonne.ufr27.miage.java.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;


    @Override
    public void configure() throws Exception {
        System.out.println("test");
        from("sjms2:topic:" + jmsPrefix + "reply")
                .log("yeyeye")
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
                        exchange.getMessage().setHeader("contentType", "application/pdf");
                        exchange.getMessage().setHeader("subject", "Contrat Juridique");
                    }
                })
                //processor pour signer le contrat
                .to("smtps://smtp.univ-paris1.fr:465?username=hugo.marques@etu.univ-paris1.fr&password=MIAGETasvee12");
    }
}