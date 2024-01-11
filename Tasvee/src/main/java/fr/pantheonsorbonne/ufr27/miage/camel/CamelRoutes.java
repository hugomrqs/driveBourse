package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.Interet;
import fr.pantheonsorbonne.ufr27.miage.service.InteretService;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
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

        //recois du smtp gateway
        from("direct:smtp")
                .autoStartup(isRouteEnabled)
                .choice()
                //oriente selon le header
                .when(header("subject").in("BM", "CJ", "EF", "EJ", "CJOPBP"))
                .marshal().json()
                //.to("pdf:create")
                .to("file:target/data?fileName=bmID-${in.headers.bmID}.json")
                .otherwise()
                .log("Domaine non pris en charge");


        /////////////////////
        //// Presta Juridique
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "BM")
                .autoStartup(isRouteEnabled)
                .log("${body}")
                .unmarshal().json(BusinessModelDTO.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        BusinessModelDTO notice = exchange.getMessage().getBody(BusinessModelDTO.class);
                        exchange.getMessage().setBody("Bonjour," +
                                "\n\n Suite à votre prise de contact sur notre site via l'offerForm numéro :  " + notice.idBusinessModel() +
                                "\n\n Tasvee à le plaisir de vous annocer notre collaboration et vous propose une levéee à " +  notice.objectifLeveeExperienceTasvee() +
                                " \n\n Veuillez trouver ci joint votre Business plan plus détaillé" +
                                "\n\n En vous remerciant par avance" +
                                "\n\n Tasvee");

                        AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);

                    }
                })
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");

        /////////////////////
        //// Business Model
        /////////////////////

        from("file:target/data")
                .autoStartup(isRouteEnabled)
                .log("${body}")
                .unmarshal().json(BusinessModelDTO.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        BusinessModelDTO notice = exchange.getMessage().getBody(BusinessModelDTO.class);
                        exchange.getMessage().setHeaders(new HashMap<>());
                        exchange.getMessage().setHeader("from",smtpUser);
                        exchange.getMessage().setHeader("contentType", "text/HTML");
                        exchange.getMessage().setBody("Bonjour," +
                                "\n\n Suite à votre prise de contact sur notre site via l'offerForm numéro :  " + notice.idBusinessModel() +
                                "\n\n Tasvee à le plaisir de vous annocer notre collaboration et vous propose une levéee à " +  notice.objectifLeveeExperienceTasvee() +
                                " \n\n Veuillez trouver ci joint votre Business plan plus détaillé" +
                                "\n\n En vous remerciant par avance" +
                                "\n\n Tasvee");

                        AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);
                       attMsg.addAttachment("bmID-"+notice.idBusinessModel().toString()+".json", new DataHandler(new FileDataSource("target/data")));



                    }
                })
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");



        //////////////////////////////////////////////////

        /////////////////////
        //// CjBM
        /////////////////////

        from("file:data999")
                .autoStartup(isRouteEnabled)
                .log("${body}")
                .unmarshal().json(ContratJuridiqueBMDTO.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        ContratJuridiqueBMDTO notice = exchange.getMessage().getBody(ContratJuridiqueBMDTO.class);
                        exchange.getMessage().setBody("Bonjour," +
                                "\n\n Si vous recevez ce mail c'est ce que vous avez recu un BusinessModel :  " + notice.idBusinessModel() +
                                "\n\n Vous trouverez ci-joint un Contrat Juridique pour la société  X : " + notice.siretTasvee() +
                                " \n\n "+
                                "\n\n Ce contrat stipule que la société Tasvee :" + notice.siretTasvee() + "s'engage avec vous à condition de  "+ notice.pourcentageComissionTasvee()+"% de commissions réalisée" +
                                "\n\n En vous remerciant par avance" +
                                "\n\n Tasvee");

                        AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);
                      //  attMsg.addAttachment("bmID-"+notice.idBusinessModel().toString()+".json", new DataHandler(new FileDataSource(new File("data"))));



                    }
                })
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");



        //////////////////////////////////////////////////

    }
}
