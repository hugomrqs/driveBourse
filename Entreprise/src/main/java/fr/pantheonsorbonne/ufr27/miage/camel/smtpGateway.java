package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
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
//    public void sendBusinessModelToStartUp(BusinessModel bm) {
//        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
//            producerTemplate.sendBodyAndHeaders(  "direct:smtp" ,new BusinessModelDTO(bm.getIdBusinessModel(),bm.getArgentLeveeXpTasvee(), bm.getPartCedeeXpTasvee()),
//                    Map.of("subject","BM",
//                            "bmID",bm.getIdBusinessModel()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
