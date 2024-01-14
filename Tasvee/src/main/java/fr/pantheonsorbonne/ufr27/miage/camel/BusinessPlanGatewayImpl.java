package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class BusinessPlanGatewayImpl implements BusinessPlanGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    public void sendBusinessPlan(BusinessPlan businessPlan, int toFondSiretQueue) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            String responseDestination = "sjms2:topic:" + jmsPrefix + "businessPlanForFond" + toFondSiretQueue;
            producerTemplate.sendBodyAndHeader(responseDestination, businessPlan, "SiretStartUp", businessPlan.siretEntreprise());
        } catch (Exception e) {
            throw new RuntimeException("Error sending Business Plan", e);
        }
    }
}
