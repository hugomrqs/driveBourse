package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

public class smtpGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;


    public void replyToOffer(fr.pantheonsorbonne.ufr27.miage.model.BusinessModel bm, String mail, String mail1) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody(   "jms:queue:"+jmsPrefix+"replyOffer",bm);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
