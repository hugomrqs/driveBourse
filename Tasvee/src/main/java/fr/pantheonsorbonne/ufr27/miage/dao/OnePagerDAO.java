package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciereEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridiqueEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;

public interface OnePagerDAO {
    void createOnePager(StartUpEntity startUp,
                        ExpertiseJuridiqueEntity expertiseJuridiqueEntity,
                        ExpertiseFinanciereEntity expertiseFinanciereEntity);
    OnePagerEntity selectOnePagerByIdStartUp(int siretStartUp) throws OnePagerNotFoundException;
    OnePagerEntity selectOnePagerById(int idOnePager);
}
