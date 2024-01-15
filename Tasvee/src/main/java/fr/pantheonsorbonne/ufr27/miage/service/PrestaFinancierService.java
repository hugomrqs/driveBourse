package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;

public interface PrestaFinancierService {
    void requestForFinanceExpertise(Integer idBilanComptable);

    void registerFinancialExpertise(ExpertiseFinanciereDTO expertiseFinanciereDTO);
}
