package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;

public interface PrestaFinancierService {
    void requestForFinanceExpertise(Integer idBilanComptable);

    void registerFinancialExpertise(ExpertiseFinanciere expertiseFinanciere);
}
