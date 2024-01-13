package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.BMEntity;
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
        BMEntity BMEntity = new BMEntity() ;
        BMEntity.setIdBusinessModel(businessModel.idBusinessModel());
        BMEntity.setArgentLeveeXpTasvee(businessModel.argentLeveeXpTasvee());
        BMEntity.setPartCedeeXpTasvee(businessModel.partCedeeXpTasvee());
        em.persist(BMEntity);
    }
}
