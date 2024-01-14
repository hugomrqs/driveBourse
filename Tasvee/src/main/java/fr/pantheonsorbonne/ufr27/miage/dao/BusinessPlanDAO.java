package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessPlanEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;

public interface BusinessPlanDAO {
    void createBusinessPlan(StartUpEntity startUp, OnePagerEntity onePagerEntity);
    BusinessPlanEntity selectBusinessPlan(int siretStartup) throws BusinessPlanNotFoundException;

}
