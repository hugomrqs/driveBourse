package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessPlan;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;

public interface BusinessPlanDAO {
    void createBusinessPlan(StartUpEntity startUp, OnePager onePager);
    BusinessPlan selectBusinessPlan(int siretStartup) throws BusinessPlanNotFoundException;

}
