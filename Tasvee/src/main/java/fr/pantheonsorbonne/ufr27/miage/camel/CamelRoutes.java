package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.*;
import fr.pantheonsorbonne.ufr27.miage.service.InteretService;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mail.Multipart;
import jakarta.mail.internet.InternetHeaders;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;


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

//    PrestaFinancierService pf;
//
//    PrestaJuridiqueServiceImpl pj;

    @Inject
    InteretService interetService;
    @Inject
    FundsInterestedGateway fundsInterestedGateway;

    @Override
    public void configure() throws Exception {


        ////////////////////////////////////////////
        ///// PC Messaging OnePager
        ////////////////////////////////////////////


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

        ////////////////////////////////////////////
        ///// Hugo SMTP
        ////////////////////////////////////////////

        String destinaire = "smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword;
        //recois du smtp gateway

        from("direct:smtp")
                .autoStartup(isRouteEnabled)
                .marshal().json()
                .choice()
                //.when(header("type").in("PDF", "JSON"))
                .when(header("Piece-Jointe").isEqualTo("true"))
                .multicast()
                .to("sjms2:topic:" + jmsPrefix + "create-","sjms2:topic:" + jmsPrefix + "-Tasvee-${in.headers.subject}");


        ////trcois une queue de message et la poste sur smtp

    from("sjms2:topic:" + jmsPrefix + "sender")
            .to(destinaire);



    //////Creation des pièces jointes


        /////////////////////
        ////Tasvee --> StartUp Business Model PDF de préférence sinon message
        /////////////////////



//OP

        from("sjms2:topic:" + jmsPrefix + "create-PDF")
                .to("pdf:create")
                .log("${body}")
                .to("file:data/json/?fileName=bmID-${in.headers.bmID}-555ca.json");

        from("direct:create-JSON")
                .to("file:data/JSON/?fileName=bmID-${in.headers.bmID}-ca.json");

        from("sjms2:topic:" + jmsPrefix + "-Tasvee-BM")
                .log("${body}")
                .unmarshal().json(BusinessModelDTO.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        BusinessModelDTO notice = exchange.getMessage().getBody(BusinessModelDTO.class);
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("subject", "Send BM");
                        exchange.getMessage().setHeader("contentType", "text/html");
                        String Path ="C://Users/Hugo/L3_MIAGE_operating_systems/drive/Tasvee/data/Receipt.pdf";
                        String body = "<a href='"+Path+"' c'est qu'il faut cliquer/a>";
                        exchange.getMessage().setBody(body);
                    }
                })
                .log("${body}")
                .to("sjms2:topic:" + jmsPrefix + "sender" );



        //////////////////////////////////////////////////
//                .autoStartup(isRouteEnabled)
//                .marshal().json(JsonLibrary.Jackson)
//                //.to("pdf:create?pageSize=A4")
//                .to("file:data/?fileName=bmID-${in.headers.bmID}-ca.json");
//
//        from("file:data/")
//        from("sjms2:topic:" + jmsPrefix + "BM")
//                .autoStartup(isRouteEnabled)
//                .marshal().json(JsonLibrary.Jackson)
//                .to("pdf:create?pageSize=A4")
//                .to("file:target/data?fileName=bmID-ca.json")
//                .unmarshal().json();
//        from("file:target/data")
//       from("sjms2:topic:" + jmsPrefix + "BM")

        //                from("imap:" + imapHost + ":" + imapPort + "?username=" + smtpUser + "&password=" + smtpPassword +"&unseen=true&delay=6")
//                        .to("direct:reply")
//                        .onException(Exception.class)
//                        .log("Exception during IMAP polling: ${exception.message}")
//                        .handled(true)
//                        .end();
//
//                from("direct:reply")
//                        .log("ererrere")
//                                .end();


        /////////////////////
        //// Tasvee -->  Contrat Juridique pièce jointe JSON besoins de signer
        /////////////////////



        from("sjms2:topic:" + jmsPrefix + "-Tasvee-CJ")
                .autoStartup(isRouteEnabled)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("cc",smtpUser);
                        exchange.getMessage().setHeader("subject","Contrat Juridique");
                        exchange.getMessage().setHeader("contentType", "application/JSON");
                    }
                })
                .to("sjms2:topic:" + jmsPrefix + "sender");



        //////////////////////////////////////////////////


        /////////////////////
        //// Tasvee -->  Presta Juridique message
        /// ASK
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "-Tasvee-EJ")
                .autoStartup(isRouteEnabled)
                .unmarshal().json(Statut.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        Statut notice = exchange.getMessage().getBody(Statut.class);
                        exchange.getMessage().setBody("Bonjour," +
                                "\n\n Nous osuhaitons  :  " + notice.nombrePart() + " de parts" +
                                "\n\n Le prix des parts actuel est de  " +  notice.prixPartActuel() +
                                " \n\n La stratégie que nous voulons aborder est " + notice.strategieEntrepreneur() +
                                "\n\n En vous remerciant par avance" +
                                "\n\n Tasvee");
                    }
                })
                .to("sjms2:topic:" + jmsPrefix + "-presta-EJ");

        ///////////////////////
        /////////////////////
        //// Presta Juridique message --> Tasvee
        /// traite reply
        /////////////////////
//            from("sjms2:topic:" + jmsPrefix + "-presta")
//                    .autoStartup(isRouteEnabled)
//                    .unmarshal().json(ExpertiseJuridique.class)
//                    .bean(pj,"registerLegalExpertise")
//                    .marshal().json()
//                    .end();


        /////////////////////
        //// Tasvee --> Presta Financier message
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "-Tasvee-EF")
                .autoStartup(isRouteEnabled)
                .unmarshal().json(BilanComptable.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        BilanComptable notice = exchange.getMessage().getBody(BilanComptable.class);
                        exchange.getMessage().setBody("Bonjour," +
                                "\n\n :  " + notice.emplois() + notice.ressources()+

                                "\n\n Tasvee");

                    }
                })
                .to("sjms2:topic:" + jmsPrefix + "sender");

        ///////////////////////

        /////////////////////
        //// Presta Fiancier --> Tasvee
        /// traite reply
        /////////////////////
//        from("sjms2:topic:" + jmsPrefix + "presta")
//                .autoStartup(isRouteEnabled)
//                .unmarshal().json(ExpertiseJuridique.class)
//                .bean(pf,"registerFinancialExpertise")
//                .marshal().json()
//                .end();



        /////////////////////
        //// CjBM  json il doivent signer
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "-Tasvee-CJOPBP")
                .autoStartup(isRouteEnabled)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("to",smtpUser);
                        exchange.getMessage().setHeader("cc",smtpUser);
                        exchange.getMessage().setHeader("subject","JSON");
                        exchange.getMessage().setHeader("contentType", "application/JSON");
                    }
                })
                .to("sjms2:topic:" + jmsPrefix + "sender" )
                .to("sjms2:topic:" + jmsPrefix + "-Fond-CJOPBP" );





        //////////////////////////////////////////////////

    }
}
