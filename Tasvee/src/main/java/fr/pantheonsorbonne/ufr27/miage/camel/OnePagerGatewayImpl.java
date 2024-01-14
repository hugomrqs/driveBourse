package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class OnePagerGatewayImpl implements OnePagerGateway {
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Override
    public void sendOnePager(OnePager onePager) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            String responseDestination = "sjms2:" + jmsPrefix + "queue:interestedIn";
            producerTemplate.sendBodyAndHeaders(
                    "direct:OnePager",
                    onePager, Map.of(
                    "Secteur", onePager.domaine(),
                    "ReplyTo", responseDestination
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
