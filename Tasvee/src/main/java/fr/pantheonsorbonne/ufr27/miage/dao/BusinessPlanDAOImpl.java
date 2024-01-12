package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessPlan;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.ejb.Asynchronous;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BusinessPlanDAOImpl implements BusinessPlanDAO{
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void createBusinessPlan(StartUpEntity startUp, OnePager onePager) {
        BusinessPlan businessPlan = new BusinessPlan(startUp, onePager);
        em.persist(businessPlan);
    }

    @Override
    public BusinessPlan selectBusinessPlan(int siretStartup) throws BusinessPlanNotFoundException {
        try {
            BusinessPlan businessPlan = (BusinessPlan) em.createQuery(
                            "SELECT c FROM BusinessPlan c WHERE c.siretStartUp.siretStartUp = :siretStartup")
                    .setParameter("siretStartup",siretStartup)
                    .getSingleResult();
            return businessPlan;
        } catch (NoResultException e) {
            throw new BusinessPlanNotFoundException(siretStartup);
        }
    }

}
