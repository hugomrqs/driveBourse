package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ContractNotSignedException;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;

public interface BusinessPlanService {

    void createBusinessPlan(int siretEntreprise)
            throws OnePagerNotFoundException, StartUpNotFoundException;
    void sendBusinessPlan(int siretEntreprise, int siretFond)
            throws
            BusinessPlanNotFoundException,
            StartUpNotFoundException,
            OnePagerNotFoundException, ContractNotSignedException;
}
