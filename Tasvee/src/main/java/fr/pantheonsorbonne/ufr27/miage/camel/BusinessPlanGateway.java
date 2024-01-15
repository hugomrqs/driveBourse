package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;

public interface BusinessPlanGateway {
    void sendBusinessPlan(BusinessPlanDTO businessPlanDTO, int toFondSiretQueue);
    }
