package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerService;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;


public class OnePagerGatewayImpl implements OnePagerGateway {
    @Inject
    OnePagerService onePagerService;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    public void sendOnePager(int siretStartUp) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            OnePager onePager =  onePagerService.sendOnePager(siretStartUp);
            producerTemplate.sendBodyAndHeader("direct:OnePager", onePager, "secteur", onePager.domaine() );
        } catch (IOException | StartUpNotFoundException | OnePagerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
