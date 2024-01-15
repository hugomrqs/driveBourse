package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciereEntity;

public interface ExpertiseFinanciereDAO {
    ExpertiseFinanciereEntity selectExpertiseFinanicereFromSiret(int siretStartup)
            throws StartUpNotFoundException;
    ExpertiseFinanciereEntity findById(Integer id);
    void registerExpertiseFinanciere(ExpertiseFinanciereDTO expertiseFinanciereEntity);
}
