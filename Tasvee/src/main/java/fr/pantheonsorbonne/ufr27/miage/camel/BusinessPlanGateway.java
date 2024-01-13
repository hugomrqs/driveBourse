package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlan;

public interface BusinessPlanGateway {
    void sendBusinessPlan(BusinessPlan businessPlan, int toFondSiretQueue);
    }
