package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessPlan;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped
public class BusinessDAOImpl implements BusinessDAO {

    @Override
    public void createNewBusinessPlan(BusinessPlanDTO bm) {
        BusinessPlan businessPlan = new BusinessPlan();
        businessPlan.setSiretStartUp(bm.siretEntreprise());
        //businessPlan.setIDOnePager();
        // EXPLICATION PLEASE, LE DTO ET LE MODEL SONT COMLETEMENT DIFFERENT
    }

    @Override
    public Proposition createRandomProposition(BusinessPlanDTO bp) {
        Proposition p = new Proposition();
        Random random = new Random();
        p.setSiretFonds(bp.siretEntreprise());
        p.setPourcentagePart(random.nextInt(30) + 10);
        p.setLeveeDeFonds(random.nextInt(9000)+1000);
        return p;
    }
}
