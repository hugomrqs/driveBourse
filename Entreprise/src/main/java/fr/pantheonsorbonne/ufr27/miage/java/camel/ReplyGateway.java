package fr.pantheonsorbonne.ufr27.miage.java.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.java.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.java.model.ContratJuridiqueOnePagerPourBP;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

public class ReplyGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;


    public void replyToOffer(ContratJuridiqueOnePagerPourBP cj) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            ObjectMapper objectMapper = new ObjectMapper();
            producerTemplate.sendBody(   "sjms2:topic:" + jmsPrefix + "-SMTP-Reply" ,objectMapper.writeValueAsString(new BusinessModelDTO(bm.getIdBusinessModel(),bm.getArgentLeveeXpTasvee(), bm.getPartCedeeXpTasvee())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
