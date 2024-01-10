package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
import fr.pantheonsorbonne.ufr27.miage.model.StartUp;

public interface OnePagerDAO {
    void createOnePager(StartUp startUp,
                            ExpertiseJuridique expertiseJuridique,
                            ExpertiseFinanciere expertiseFinanciere)
            throws StartUpNotFoundException;
    OnePager selectOnePagerByIdStartUp(int siretStartUp) throws OnePagerNotFoundException;
}
