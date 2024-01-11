package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;

public interface BusinessService {

    /* Renvoie true si la proposition est accepté et false sinon */
    public void createPropfromBP(BusinessPlanDTO bm);


}
