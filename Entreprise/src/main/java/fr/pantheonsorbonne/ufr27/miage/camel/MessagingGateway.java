package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class MessagingGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    public void sendNDA(NDADTOCommercialisationDTO nda) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            System.out.println("Le contrat a été signé par Entrepreneur et s'envoie à Tasvee");
            producerTemplate.sendBody("direct:sendNDA", nda);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
