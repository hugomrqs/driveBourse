package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class MessagingGateway {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Inject
    PropositionService propService;


    public void sendProposal(PropositionDTO prop) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("direct:sendProposal", prop ,"etatProp",prop.etatProposition());
            System.out.println("Une proposition a été envoyé à Tasvee");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendSignedNDACom(NDADTOCommercialisationDTO nda) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:signedNDA", nda );
            System.out.println("Le contrat a été signé par Fond et va être envoyé à Tasvee");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMoney(int argent, String sender) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBody("direct:moneyFor"+sender, argent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
