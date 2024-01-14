package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerInteretService;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;
import fr.pantheonsorbonne.ufr27.miage.service.PrestaFinancierService;
import fr.pantheonsorbonne.ufr27.miage.service.PrestaJuridiqueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;


@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;
    @Inject
    OnePagerInteretService onePagerInteretService;
    @Inject
    PropositionService propositionService;
    @Inject
    FundsInterestedGateway fundsInterestedGateway;


    @Inject
    PrestaJuridiqueService prestaJuridiqueService ;

    @Inject
    PrestaFinancierService prestaFinancierService ;

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

        from("direct:OnePager")
                .autoStartup(isRouteEnabled)
                .log("OnePager : Secteur = ${in.headers}")
                .marshal().json()
                .choice()
                    .when(header("Secteur").in("T", "S", "I", "F", "E"))
                        .toD("sjms2:topic:" + jmsPrefix + "${in.headers.Secteur}")
                        .log("sjms2:topic:" + jmsPrefix + "${in.headers.Secteur}")
                    .otherwise()
                        .log("Domaine non pris en charge");

//        from("sjms2:topic:"+ jmsPrefix +"Tech") route que fond utilise
//                .log("OnePager: ${in.headers} ${in.body}");

        from("sjms2:" + jmsPrefix + "queue:interestedIn")
                .filter(header("IsInterested").isEqualTo(true))
                .unmarshal().json(OnePagerInteret.class)
                .aggregate(header("idOnePager"), new InteretAgregationStrategy())
                .completionSize(2)
                .completionTimeout(10000)
                .to("direct:processInteretOnePager");

//  @TODO      from("direct:processInteretOnePager")
//                // Créer le contrat en base de données
//                .bean(contratJuridiqueService, "createContratJuridiqueOnePagerPourBPDatabase")
//                // Créer le DTO
//               contratJuridiqueService, "createContratJuridiqueOnePagerPourBPDTO")
//                // Envelopper le DTO dans un message Camel et préparer pour l'envoi
//                .bean(ContratJuridiqueGateway, "sendContratJuridiqueOnePagerPourBP()");
//                "contratJuridiqueService,"createContratJuridiqueOnePagerPourBPDTO\""


        from("sjms2:topic:" + jmsPrefix + "proposalForTasvee")
                .autoStartup(isRouteEnabled)
                .log("Proposition de Fond reçu")
                .choice()
                .when(simple("${header.etatProp} == 'Accepter'"))
                .log("Proposition de Fond accepté")
                .bean(propositionService, "addLastProposal")
                .when(simple("${header.etatProp} == 'Refuser'"))
                .log("Proposition de Fond refusé")
                .unmarshal().json(PropositionDTO.class)
                .bean(propositionService, "challengeProposal").marshal().json()
                .end();

        from("direct:sendProposal")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "proposalForFond");

        from("direct:sendContratTrip")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "NDACommercialForFond");

        from("sjms2:topic:" + jmsPrefix + "signedNDAForTasvee")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "NDACommercialForEntrepreneur");


        from("sjms2:topic:" + jmsPrefix + "signedNDAForTasvee")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .bean(propositionService, "insertNDA").marshal().json();

        from("direct:ribOfTasvee")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "ribOfTasvee");

        from("direct:ribOfEntrepereneur")
                .autoStartup(isRouteEnabled)
                .log("proposition Envoyé")
                .marshal().json()
                .to("sjms2:topic:" + jmsPrefix + "ribOfEntrepereneur");


        /////////////////////
        //// Tasvee --> Presta Juridique message
        //// Ask
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "-Tasvee-EJ")
                .autoStartup(isRouteEnabled)
                .unmarshal().json(StatutDTO.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        StatutDTO notice = exchange.getMessage().getBody(StatutDTO.class);
                        exchange.getMessage().setBody("Bonjour," +
                                "\n\n Nous osuhaitons  :  " + notice.nombrePart() + " de parts" +
                                "\n\n Le prix des parts actuel est de  " +  notice.prixPartActuel() +
                                " \n\n La stratégie que nous voulons aborder est " + notice.strategieEntrepreneur() +
                                "\n\n En vous remerciant par avance" +
                                "\n\n Tasvee");
                    }
                })
                .to("sjms2:topic:" + jmsPrefix + "sender");



        /////////////////////
        //// Tasvee --> Presta Financier message
        //// Ask
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "-Tasvee-EF")
                .autoStartup(isRouteEnabled)
                .unmarshal().json(BilanComptableDTO.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        BilanComptableDTO notice = exchange.getMessage().getBody(BilanComptableDTO.class);
                        exchange.getMessage().setBody("Bonjour," +
                                "\n\n :  " + notice.emplois() + notice.ressources()+

                                "\n\n Tasvee");

                    }
                })
                .to("sjms2:topic:" + jmsPrefix + "sender");



        /////////////////////
        //// Presta Fiancier --> Tasvee
        /// traite reply
        /////////////////////

        from("sjms2:topic:" + jmsPrefix + "EF")
                .autoStartup(isRouteEnabled)
                .unmarshal().json(ExpertiseJuridiqueDTO.class)
                .bean(prestaFinancierService,"registerFinancialExpertise")
                .marshal().json()
                .end();


        /////////////////////
        //// Presta Juridique --> Tasvee
        /// traite reply
        /////////////////////

            from("sjms2:topic:" + jmsPrefix + "EJ")
                    .autoStartup(isRouteEnabled)
                    .unmarshal().json(ExpertiseJuridiqueDTO.class)
                    .bean(prestaJuridiqueService,"registerLegalExpertise")
                    .marshal().json()
                    .end();


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
                .to("sjms2:topic:" + jmsPrefix + "sender" );


    }
}