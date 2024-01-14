package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;

public interface PrestaFinancierService {
    void requestForFinanceExpertise(Integer idBilanComptable);

    void registerFinancialExpertise(ExpertiseFinanciere expertiseFinanciere);
}
