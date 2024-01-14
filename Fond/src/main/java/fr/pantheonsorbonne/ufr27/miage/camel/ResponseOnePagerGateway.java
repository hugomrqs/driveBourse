package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerResponse;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
import fr.pantheonsorbonne.ufr27.miage.service.TraitementOnePagerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        OnePagerInteret onePagerInteret = new OnePagerInteret(helper.siret, onePager.idOnePager(), traitementOnePagerService.OnePagerResponse(onePager));
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("IsInterested", onePagerInteret.isInterested());
            headers.put("idOnePager", onePagerInteret.idOnePager());
            producerTemplate.sendBodyAndHeaders(
                    "direct:cool",
                    onePagerInteret,
                    headers
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
