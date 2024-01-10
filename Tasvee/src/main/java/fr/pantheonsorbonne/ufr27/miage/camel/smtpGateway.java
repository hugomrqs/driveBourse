package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBM;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class smtpGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;


    public void replyToOffer(BusinessModel bm) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader(  "direct:smtp" ,(new BusinessModelDTO(bm.getIdBusinessModel(),bm.getArgentLeveeXpTasvee(), bm.getPartCedeeXpTasvee())),"subject","BM");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendContratJuridiqueBMtoStartUp(ContratJuridiqueBM contratJuridiqueBM, String mail) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            System.out.println("testvvvvv");

            producerTemplate.sendBodyAndHeader("direct:smtp",contratJuridiqueBM,"subject","BM");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
