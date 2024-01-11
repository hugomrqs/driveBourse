package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessPlan;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessDAOImpl implements BusinessDAO {

    @Override
    public void createNewBusinessPlan(BusinessPlanDTO bm) {
        BusinessPlan businessPlan = new BusinessPlan();
        businessPlan.setSiretStartUp(bm.siretEntreprise());
        //businessPlan.setIDOnePager();
        // EXPLICATION PLEASE, LE DTO ET LE MODEL SONT COMLETEMENT DIFFERENT
    }
}
