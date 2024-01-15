package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

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
    public void sendSignedCJ(ContratJuridiqueBMDTO contratJuridiqueBM) {
        System.out.println("Ca passe dans sendDignedCJ");
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody(  "direct:startup-smtp" ,contratJuridiqueBM); ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}