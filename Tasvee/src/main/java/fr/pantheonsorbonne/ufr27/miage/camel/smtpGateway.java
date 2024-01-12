package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueOnePagerPourBPRecordDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBM;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class smtpGateway {

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
            producerTemplate.sendBodyAndHeaders(  "direct:smtp-BM" ,new BusinessModelDTO(bm.getIdBusinessModel(),bm.getArgentLeveeXpTasvee(), bm.getPartCedeeXpTasvee()),
                    Map.of("subject","BM",
                            "ID",bm.getIdBusinessModel()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendContratJuridiqueBMtoStartUp(ContratJuridiqueBM contratJuridiqueBM) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders("direct:smtp",contratJuridiqueBM,
                    Map.of("subject","CJ",
                            "ID",contratJuridiqueBM.getContratJuridiqueBMID()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /////////////////////
    ////Presta Fin
    /////////////////////

    public void askExpertFin() {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("direct:smtp","subject","EF");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /////////////////////
    //// Presta Juridique
    /////////////////////

    public void askExpertJur(ContratJuridiqueOnePagerPourBPRecordDTO cjbp) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders("direct:smtp", cjbp,  Map.of("subject","CJ",
                    "ID",cjbp.contratJuridiqueBM(),
                    "from",smtpUser));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /////////////////////
    //// Investisseur
    /////////////////////

    public void sendCJOnePagerPourBP() {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            System.out.println("testvvvvv");

            producerTemplate.sendBodyAndHeader("direct:smtp","subject","Contrat Juridique");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
