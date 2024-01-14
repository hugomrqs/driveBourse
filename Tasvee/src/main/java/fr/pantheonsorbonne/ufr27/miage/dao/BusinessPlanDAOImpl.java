package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessPlanEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
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
    public void createBusinessPlan(StartUpEntity startUp, OnePagerEntity onePagerEntity) {
        BusinessPlanEntity businessPlanEntity = new BusinessPlanEntity(startUp, onePagerEntity);
        em.persist(businessPlanEntity);
    }

    @Override
    public BusinessPlanEntity selectBusinessPlan(int siretStartup) throws BusinessPlanNotFoundException {
        try {
            BusinessPlanEntity businessPlanEntity = (BusinessPlanEntity) em.createQuery(
                            "SELECT c FROM BusinessPlanEntity c WHERE c.siretStartUp.siretStartUp = :siretStartup")
                    .setParameter("siretStartup",siretStartup)
                    .getSingleResult();
            return businessPlanEntity;
        } catch (NoResultException e) {
            throw new BusinessPlanNotFoundException(siretStartup);
        }
    }

}
