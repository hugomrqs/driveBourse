package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.service.PrestaFinancierService;
import fr.pantheonsorbonne.ufr27.miage.service.PrestaJuridiqueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;


@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    PrestaJuridiqueService prestaJuridiqueService ;

    @Inject
    PrestaFinancierService prestaFinancierService ;

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

        ////////////////////////////////////////////
        ///// Hugo SMTP
        ////////////////////////////////////////////

        String destinaire = "smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword;
        //recois du smtp gateway

//        from("direct:smtp-bm")
//                .autoStartup(isRouteEnabled)
//                .marshal().json()
//                .log("SMTP entrée///////////////////////")
//                .log("${body}")
//                .choice()
//                .when(header("subject").in("BM", "CJ", "EF", "EJ", "CJOPBM"))
//                .toD("sjms2:topic:" + jmsPrefix + "-Tasvee-${in.headers.subject}");

        from("sjms2:topic:" + jmsPrefix + "sender")
                .throttle(1).timePeriodMillis(5000) // 1 message toutes les 10 secondes
                .to(destinaire);


        /////////////////////
        //// Business Model PDF de préférence sinon message
        /////////////////////
//OP
        from("direct:smtp-bm")
                .autoStartup(isRouteEnabled)
                .marshal().json()
                .log("///////////////////////")
                .log("${body}")
                .to("sjms2:topic:"+jmsPrefix+"-StartUp-BM");


        from("direct:smtp-cj")
                .autoStartup(isRouteEnabled)
                .log("///////////////////////")
                .marshal().json()
                .log("${body}")
                .to("sjms2:topic:"+jmsPrefix+"-StartUp-CJ");
        //        .unmarshal().json(BusinessModel.class)
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                        BusinessModel notice = exchange.getMessage().getBody(BusinessModel.class);
//                        exchange.getMessage().setHeaders(new HashMap<>());
//                        exchange.getMessage().setHeader("from",smtpUser);
//                        exchange.getMessage().setHeader("to",smtpUser);
//                        exchange.getMessage().setHeader("contentType", "text/html");
//                        exchange.getMessage().setHeader("subject", "Send BM");
//                        String Path ="C://Users/Hugo/L3_MIAGE_operating_systems/drive/Tasvee/data/PDF/bmID-"+notice.idBusinessModel()+".pdf";
//                        exchange.getMessage().setBody("Cher(e) Client(e)," +
//                                "<br> Nous avons bien reçu votre demande sur notre site via l'offerForm. L'identifiant ed votre Offer Form est  " + notice.idBusinessModel() +
//                                "<br> C'est avec grand plaisir que Tasvee annonce notre future collaboration. Nous sommes ravis de vous proposer une levée de fonds à " +
//                                "<br> Vous trouverez ci-joint un Business Plan détaillé pour votre consultation." +
//                                "<br> Nous vous remercions pour votre confiance et restons à votre disposition pour toute question." +
//                                "<br><a href='"+Path+"' c'est qu'il faut cliquer/a>"+
//                                "<br> Cordialement," +
//                                "<br> L'équipe Tasvee");
//                    }
//                })

                //.to("sjms2:topic:" + jmsPrefix + "sender");

     ;
        /////////////////////
        //// Contrat Juridique pièce jointe JSON
        //// besoin de signer
        /////////////////////

//        from("sjms2:topic:" + jmsPrefix + "-Tasvee-CJ")
//                .autoStartup(isRouteEnabled)
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                        exchange.getMessage().setHeaders(new HashMap<>());
//                        exchange.getMessage().setHeader("from",smtpUser);
//                        exchange.getMessage().setHeader("to",smtpUser);
//                        exchange.getMessage().setHeader("cc",smtpUser);
//                        exchange.getMessage().setHeader("subject","Contrat Juridique");
//                        exchange.getMessage().setHeader("contentType", "application/JSON");
//                    }
//                })
//                .to("sjms2:topic:"+jmsPrefix+"-StartUp-CJ");
                //.to("sjms2:topic:" + jmsPrefix + "sender");


        /////////////////////
        //// Tasvee --> Presta Juridique message
        //// Ask
        /////////////////////

//        from("sjms2:topic:" + jmsPrefix + "-Tasvee-EJ")
//                .autoStartup(isRouteEnabled)
//                .unmarshal().json(Statut.class)
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                        Statut notice = exchange.getMessage().getBody(Statut.class);
//                        exchange.getMessage().setBody("Bonjour," +
//                                "\n\n Nous osuhaitons  :  " + notice.nombrePart() + " de parts" +
//                                "\n\n Le prix des parts actuel est de  " +  notice.prixPartActuel() +
//                                " \n\n La stratégie que nous voulons aborder est " + notice.strategieEntrepreneur() +
//                                "\n\n En vous remerciant par avance" +
//                                "\n\n Tasvee");
//                    }
//                })
//                .to("sjms2:topic:" + jmsPrefix + "sender");
//


        /////////////////////
        //// Tasvee --> Presta Financier message
        //// Ask
        /////////////////////

//        from("sjms2:topic:" + jmsPrefix + "-Tasvee-EF")
//                .autoStartup(isRouteEnabled)
//                .unmarshal().json(BilanComptable.class)
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                        BilanComptable notice = exchange.getMessage().getBody(BilanComptable.class);
//                        exchange.getMessage().setBody("Bonjour," +
//                                "\n\n :  " + notice.emplois() + notice.ressources()+
//
//                                "\n\n Tasvee");
//
//                    }
//                })
//                .to("sjms2:topic:" + jmsPrefix + "sender");
//


        /////////////////////
        //// Presta Fiancier --> Tasvee
        /// traite reply
        /////////////////////
//
//        from("sjms2:topic:" + jmsPrefix + "EF")
//                .autoStartup(isRouteEnabled)
//                .unmarshal().json(ExpertiseJuridique.class)
//                .bean(prestaFinancierService,"registerFinancialExpertise")
//                .marshal().json()
//                .end();
//

        /////////////////////
        //// Presta Juridique --> Tasvee
        /// traite reply
        /////////////////////

//            from("sjms2:topic:" + jmsPrefix + "EJ")
//                    .autoStartup(isRouteEnabled)
//                    .unmarshal().json(ExpertiseJuridique.class)
//                    .bean(prestaJuridiqueService,"registerLegalExpertise")
//                    .marshal().json()
//                    .end();
//
//
//        /////////////////////
//        //// CjBM  json il doivent signer
//        /////////////////////
//
//        from("sjms2:topic:" + jmsPrefix + "-Tasvee-CJOPBP")
//                .autoStartup(isRouteEnabled)
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        exchange.getMessage().setHeaders(new HashMap<>());
//                        exchange.getMessage().setHeader("from",smtpUser);
//                        exchange.getMessage().setHeader("to",smtpUser);
//                        exchange.getMessage().setHeader("cc",smtpUser);
//                        exchange.getMessage().setHeader("subject","JSON");
//                        exchange.getMessage().setHeader("contentType", "application/JSON");
//                    }
//                })
//                .to("sjms2:topic:" + jmsPrefix + "sender" );
//

    }
}