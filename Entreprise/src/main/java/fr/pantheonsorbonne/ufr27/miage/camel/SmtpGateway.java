package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class SmtpGateway {
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @Inject
    CamelContext camelContext;

    /////////////////////
    ////Start UP
    /////////////////////
    public void sendSignedCJ(ContratJuridiqueBM contratJuridiqueBM) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders(  "direct:startup-smtp" ,contratJuridiqueBM,
                    Map.of("subject","Contrat juridique BM signé",
                            "CJBMID",contratJuridiqueBM.contratJuridiqueBM()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}