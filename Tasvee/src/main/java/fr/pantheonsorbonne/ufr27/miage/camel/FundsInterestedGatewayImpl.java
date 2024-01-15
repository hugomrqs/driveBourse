package fr.pantheonsorbonne.ufr27.miage.camel;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.BadDataException;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;


@ApplicationScoped
public class FundsInterestedGatewayImpl implements FundsInterestedGateway {
    @Inject
    OnePagerService onePagerService;
    @Inject
    CamelContext context;
    @Override
    public void distribute(ExpertiseFinanciereDTO expertiseFinanciere, ExpertiseJuridiqueDTO expertiseJuridique, Character c) throws BadDataException {

    }

}
