package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;

public interface StartUpDAO {
    StartUpEntity selectStartUp(int siretStartup) throws StartUpNotFoundException ;
}
