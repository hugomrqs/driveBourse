package fr.pantheonsorbonne.ufr27.miage.exception;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import jakarta.persistence.NoResultException;

public class BadDataException extends NoResultException {
        public BadDataException(ExpertiseJuridiqueDTO expertiseJuridique ,ExpertiseFinanciereDTO expertiseFinanciere) {
            super("missing data from either" + expertiseJuridique + expertiseFinanciere);
        }
}
