package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.RIBDTO;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class PropositionGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    PropositionService propService;



    public void sendProposal(PropositionDTO prop) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("direct:sendProposal", prop ,"etatProp",prop.etatProposition());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendContratTripartite(NDADTOCommercialisationDTO contr) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:sendContratTrip", contr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendRIB(RIBDTO ribEntrepreneur, RIBDTO ribTasvee) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:ribOfEntrepereneur", ribEntrepreneur);
            producerTemplate.sendBody("direct:ribOfTasvee", ribTasvee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
