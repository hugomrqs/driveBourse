package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;

public interface PrestaJuridiqueService {
    void requestForLegalExpertise(Integer idStatut);

    void registerLegalExpertise(ExpertiseJuridiqueDTO expertiseJuridiqueDTO);
}
