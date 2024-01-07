package fr.pantheonsorbonne.ufr27.miage.DAO;

import fr.pantheonsorbonne.ufr27.miage.camel.smtpGateway;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class BusinessModelDAOImpl implements BusinessModelDAO {
    @PersistenceContext(name = "mysql")
    EntityManager em;


    @Transactional
    public List listAllBusinessModels() {
        return em.createQuery("SELECT b from BusinessModel b").getResultList();
    }

    @Override
    @Transactional
    public BusinessModel getBusinessModel(int businessModelId) {
        return (BusinessModel) em.createQuery("SELECT b FROM BusinessModel b WHERE idBusinessModel = :businessModelId")
                .setParameter("businessModelId", businessModelId)
                .getSingleResult();
    }


    @Override
    @Transactional
    public void addBusinessModel(BusinessModel bm) {
        em.persist(bm);
    }


}
