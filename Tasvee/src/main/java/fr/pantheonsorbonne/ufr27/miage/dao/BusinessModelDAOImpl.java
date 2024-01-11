package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
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
    public BusinessModelEntity getBusinessModel(Integer idBusinessModel) {
        return em.find(BusinessModelEntity.class, idBusinessModel) ;
    }

    @Override
    @Transactional
    public BusinessModelEntity createBusinessModel(Integer idSiretStartup, Integer ArgentLeveeXpTasvee, Integer PartCedeeXpTasvee) {
        BusinessModelEntity businessModelEntity = new BusinessModelEntity() ;
        businessModelEntity.setArgentLeveeXpTasvee(ArgentLeveeXpTasvee);
        businessModelEntity.setPartCedeeXpTasvee(PartCedeeXpTasvee);
        businessModelEntity.setsiretStartUp(em.find(StartUpEntity.class, idSiretStartup));
        em.persist(businessModelEntity);
        return businessModelEntity;
    }

    @Override
    public void addSignature(ContratJuridiqueBMEntity contratJuridiqueBMEntity) {
        ContratJuridiqueBMEntity existingEntity = em.find(ContratJuridiqueBMEntity.class, contratJuridiqueBMEntity);
        existingEntity.setStartUp(true);
        em.merge(existingEntity);
    }
}
