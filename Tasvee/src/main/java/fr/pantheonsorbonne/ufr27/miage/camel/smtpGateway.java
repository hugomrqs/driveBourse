package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBM;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class smtpGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;


    public void replyToOffer(BusinessModel bm, String mail) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            System.out.println("testvvvvv");
            producerTemplate.sendBodyAndHeader(   "direct:smtpToStartUp",bm,"mailtp:",mail);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendContratJuridiqueBMtoStartUp(ContratJuridiqueBM contratJuridiqueBM, String mail) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            System.out.println("testvvvvv");
            producerTemplate.sendBodyAndHeader(   "jms:queue:"+jmsPrefix+"smtpToStartUp",contratJuridiqueBM,"mailtp:",mail);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
