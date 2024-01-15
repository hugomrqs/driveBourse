package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerDTO;
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
    public void sendOnePager(OnePagerDTO onePagerDTO) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            String responseDestination = "sjms2:" + jmsPrefix + "queue:interestedIn";
            producerTemplate.sendBodyAndHeaders(
                    "direct:OnePager",
                    onePagerDTO, Map.of(
                    "Secteur", onePagerDTO.domaine(),
                    "ReplyTo", responseDestination
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
