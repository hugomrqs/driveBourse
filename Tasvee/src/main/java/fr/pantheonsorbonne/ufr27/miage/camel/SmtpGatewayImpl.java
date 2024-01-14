package fr.pantheonsorbonne.ufr27.miage.camel;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOProductionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class SmtpGatewayImpl implements SmtpGateway{
    @Inject
    CamelContext camelContext;
    public void sendCJOPBP(NDADTOProductionDTO cjopbp) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeaders(  "direct:smtp" ,cjopbp,
                    Map.of("subject","BM",
                            "ID","test"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}