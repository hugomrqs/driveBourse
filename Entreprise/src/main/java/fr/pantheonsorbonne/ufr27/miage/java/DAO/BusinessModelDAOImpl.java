package fr.pantheonsorbonne.ufr27.miage.java.DAO;

import fr.pantheonsorbonne.ufr27.miage.java.model.BusinessModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
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
