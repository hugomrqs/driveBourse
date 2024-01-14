package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessPlanEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Random;

@ApplicationScoped
public class BusinessDAOImpl implements BusinessDAO {

    @Inject
    EntityManager em;

    @Override
    public void createNewBusinessPlan(BusinessPlanDTO bm) {
        BusinessPlanEntity businessPlanEntity = new BusinessPlanEntity();
        businessPlanEntity.setSiretStartUp(bm.siretEntreprise());
        //businessPlan.setIDOnePager();
        // EXPLICATION PLEASE, LE DTO ET LE MODEL SONT COMLETEMENT DIFFERENT
    }

    @Override
    public PropositionEntity createRandomProposition(BusinessPlanDTO bp) {
        PropositionEntity p = new PropositionEntity();
        Random random = new Random();
        p.setSiretFonds(bp.siretEntreprise());
        p.setPourcentagePart(random.nextInt(30) + 10);
        p.setLeveeDeFonds(random.nextInt(9000)+1000);
        em.persist(p);
        return p;
    }
}
