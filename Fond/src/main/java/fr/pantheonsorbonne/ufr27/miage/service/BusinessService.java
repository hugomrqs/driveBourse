package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;

public interface BusinessPlanService {

    /* Renvoie true si la proposition est accepté et false sinon */
    public BusinessModelDTO createBMfromBP(BusinessPlanDTO bm);


}
