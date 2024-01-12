package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;

public interface PrestaJuridiqueService {
    void requestForLegalExpertise(Integer idStatut);

    void registerLegalExpertise(ExpertiseJuridique expertiseJuridique);
}
