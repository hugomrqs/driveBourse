package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridique;

public interface ExpertiseJuridiqueDAO {
    public ExpertiseJuridique selectExpertiseJuridiqueFromSiret(int siretStartup)
            throws StartUpNotFoundException;
    public ExpertiseJuridique findById(Integer id);
}
