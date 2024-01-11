package fr.pantheonsorbonne.ufr27.miage.service.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessPlanService;
import jakarta.inject.Inject;

public class BusinessPlanServiceImpl implements BusinessPlanService {
    @Inject
    PropositionDAO propositionDao;


    public BusinessModelDTO createBMfromBP(BusinessPlanDTO bm){
        try {
*

        }catch (Exception e){
            System.out.println(e);
        }
    };

}
