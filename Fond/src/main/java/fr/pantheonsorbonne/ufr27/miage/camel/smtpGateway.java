package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;
import java.util.Map;


public class smtpGateway {

    @Inject
    CamelContext camelContext;
    public void sendSignedCJOPBP(ContratJuridiqueOnePagerPourBP cjopbp) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders(  "direct:smtp" ,cjopbp,
                    Map.of("subject","BM",
                            "ID","test"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
