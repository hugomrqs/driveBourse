package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.model.*;
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
        ExpertiseFinanciereEntity ef = new ExpertiseFinanciereEntity();
            ef.setBFRExpert(bm.expertiseFinanciere().bfrExpert().intValue());
            ef.setMargeBrutExpert(bm.expertiseFinanciere().margeBrutExpert().intValue());

        ExpertiseJuridiqueEntity ej = new ExpertiseJuridiqueEntity();
            ej.setNombrePartExpertise(bm.expertiseJuridique().nombrePartExpertise());
            ej.setPrixPartExpertise(bm.expertiseJuridique().prixActuelPartExpertise());

        OnePagerEntity op = new OnePagerEntity(ef,ej,bm.siretEntreprise());

        businessPlanEntity.setOnePager(op);
        em.persist(businessPlanEntity);
    }

    @Override
    public PropositionEntity createProposition(BusinessPlanDTO bp) {
        PropositionEntity p = new PropositionEntity();
        Random random = new Random();
        p.setSiretFonds(bp.siretEntreprise());
        p.setPourcentagePart((int) (bp.expertiseJuridique().nombrePartExpertise() * 1.2));
        p.setLeveeDeFonds((int) (bp.expertiseFinanciere().bfrExpert()*5));
        em.persist(p);
        return p;
    }
}
