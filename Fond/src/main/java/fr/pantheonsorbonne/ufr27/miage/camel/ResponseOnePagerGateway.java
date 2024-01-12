package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerResponse;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
import fr.pantheonsorbonne.ufr27.miage.service.TraitementOnePagerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class ResponseOnePagerGateway {

    @Inject
    TraitementOnePagerService traitementOnePagerService;
    @Inject
    CamelContext camelContext;
    @Inject
    Helper helper;
    public void SendResponse(OnePager onePager, String replyTo) {

        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {

            OnePagerResponse onePagerResponse =new OnePagerResponse(helper.siret,traitementOnePagerService.OnePagerResponse(onePager));

            producerTemplate.sendBodyAndHeader(
                    replyTo,
                    onePagerResponse,
                    "IsInterested",
                    onePagerResponse.isInterested()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
