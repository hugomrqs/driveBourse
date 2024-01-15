package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BMEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BusinessModelDAOImpl implements BusinessModelDAO{
    @Inject
    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public void registerBusinessModelInBDD(BusinessModelDTO businessModel) {
        BMEntity bmentity = new BMEntity(businessModel.argentLeveeXpTasvee(),businessModel.partCedeeXpTasvee());
        em.persist(bmentity);
    }
}
