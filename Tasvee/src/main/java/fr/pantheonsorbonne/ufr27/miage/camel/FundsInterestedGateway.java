package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.exception.BadDataException;

public interface FundsInterestedGateway {
    void distribute(ExpertiseFinanciere expertiseFinanciere, ExpertiseJuridique expertiseJuridique, Character c) throws BadDataException ;
}
