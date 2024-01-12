package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlan;
import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;

public interface BusinessPlanService {

    void createBusinessPlan(int siretEntreprise) throws OnePagerNotFoundException, StartUpNotFoundException;

    void sendBusinessPlan(int siretEntreprise) throws BusinessPlanNotFoundException, StartUpNotFoundException, OnePagerNotFoundException;

    // D'autres méthodes peuvent être ajoutées selon les besoins, par exemple :
    // BusinessPlan getBusinessPlan(int siretEntreprise) throws BusinessPlanNotFoundException, CompanyNotFoundException;
}
