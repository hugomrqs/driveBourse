package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;

public interface OnePagerDAO {
    void createOnePager(StartUpEntity startUp,
                        ExpertiseJuridique expertiseJuridique,
                        ExpertiseFinanciere expertiseFinanciere);
    OnePager selectOnePagerByIdStartUp(int siretStartUp) throws OnePagerNotFoundException;
    OnePager selectOnePagerById(int idOnePager);
}
