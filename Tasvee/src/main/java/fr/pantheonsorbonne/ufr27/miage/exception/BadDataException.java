package fr.pantheonsorbonne.ufr27.miage.exception;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
import jakarta.persistence.NoResultException;

public class BadDataException extends NoResultException {
        public BadDataException(ExpertiseJuridique expertiseJuridique ,ExpertiseFinanciere expertiseFinanciere) {
            super("missing data from either" + expertiseJuridique + expertiseFinanciere);
        }
}
