package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class SmtpGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @Inject
    CamelContext camelContext;

    /////////////////////
    ////Start UP
    /////////////////////
    public void sendBusinessModelToStartUp(BusinessModel bm) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders(  "direct:smtp" , bm,
                    Map.of("subject","BM",
                            "ID",bm.idBusinessModel()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendContratJuridiqueBMtoStartUp(ContratJuridiqueBM contratJuridiqueBM) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders("direct:smtp",contratJuridiqueBM,
                    Map.of("subject","CJ",
                            "ID",contratJuridiqueBM.contratJuridiqueBM()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /////////////////////
    ////Presta Fin
    /////////////////////

    public void askExpertFin(Integer idBilanComptable) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders("direct:smtp", idBilanComptable,
                    Map.of("subject","EF",
                            "ID",idBilanComptable,
                            "Endpoint url", "http://localhost:8080/bilan-comptable/"+idBilanComptable));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /////////////////////
    //// Presta Juridique
    /////////////////////

    public void askExpertJur(Integer idStatut) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders("direct:smtp", idStatut,
                    Map.of("subject","EJ",
                    "ID",idStatut,
                    "Endpoint url", "http://localhost:8080/statut/"+idStatut));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}