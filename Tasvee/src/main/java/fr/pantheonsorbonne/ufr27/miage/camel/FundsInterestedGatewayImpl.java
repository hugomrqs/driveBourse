package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
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
    public void distribute(ExpertiseFinanciere expertiseFinanciere, ExpertiseJuridique expertiseJuridique, Character c) throws BadDataException {

    }

}
