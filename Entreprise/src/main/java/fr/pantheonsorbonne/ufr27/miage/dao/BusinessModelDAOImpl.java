package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
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
    public void registerBusinessModel(BusinessModel businessModel) {
        BusinessModelEntity businessModelEntity = new BusinessModelEntity() ;
        businessModelEntity.setIdBusinessModel(businessModel.idBusinessModel());
        businessModelEntity.setArgentLeveeXpTasvee(businessModel.argentLeveeXpTasvee());
        businessModelEntity.setPartCedeeXpTasvee(businessModel.partCedeeXpTasvee());
        em.persist(businessModelEntity);
    }
}
