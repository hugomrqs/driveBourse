package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;

public interface PrestaJuridiqueService {
    void requestForLegalExpertise(Integer idStatut);

    void registerLegalExpertise(ExpertiseJuridique expertiseJuridique);
}
