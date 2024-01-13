package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.BadDataException;

public interface FundsInterestedGateway {
    void distribute(ExpertiseFinanciereDTO expertiseFinanciere, ExpertiseJuridiqueDTO expertiseJuridique, Character c) throws BadDataException ;
}
