package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciere;

public interface ExpertiseFinanciereDAO {
    ExpertiseFinanciere selectExpertiseFinanicereFromSiret(int siretStartup)
            throws StartUpNotFoundException;
    ExpertiseFinanciere findById(Integer id);
    void registerExpertiseFinanciere(ExpertiseFinanciere expertiseFinanciere);
}
