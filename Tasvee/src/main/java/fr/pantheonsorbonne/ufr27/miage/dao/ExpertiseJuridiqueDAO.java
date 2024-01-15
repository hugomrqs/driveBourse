package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridiqueEntity;

public interface ExpertiseJuridiqueDAO {
    public ExpertiseJuridiqueEntity selectExpertiseJuridiqueFromSiret(int siretStartup)
            throws StartUpNotFoundException;
    public ExpertiseJuridiqueEntity findById(Integer id);
   void registerExpertiseJuridique(ExpertiseJuridiqueDTO expertiseJuridiqueEntity);
}
