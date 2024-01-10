package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.StartUp;

public interface StartUpDAO {
    StartUp selectStartUp(int siretStartup) throws StartUpNotFoundException ;
}
