package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BusinessModelDAOImpl implements BusinessModelDAO{
    @Inject
    EntityManager em;
    @Override
    @Transactional
    public void registerBusinessModel(BusinessModelEntity businessModelEntity) {
        em.persist(businessModelEntity);
    }

    @Override
    public void registerContratJuridiqueBM(ContratJuridiqueBMEntity contratJuridiqueBMEntity) {
        em.persist(contratJuridiqueBMEntity);
    }

    @Override
    public ContratJuridiqueBMEntity sign(ContratJuridiqueBMEntity contratJuridiqueBMEntity) {
        ContratJuridiqueBMEntity existingEntity = em.find(ContratJuridiqueBMEntity.class, contratJuridiqueBMEntity);
        existingEntity.setStartUp(true);
        em.merge(existingEntity);
        return existingEntity ;
    }
}
