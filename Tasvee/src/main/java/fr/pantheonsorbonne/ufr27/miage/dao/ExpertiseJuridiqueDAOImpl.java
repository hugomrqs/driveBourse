package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridiqueEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PrestataireJuridiqueEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@ApplicationScoped
public class ExpertiseJuridiqueDAOImpl implements ExpertiseJuridiqueDAO {
    @PersistenceContext(name ="mysql")
    EntityManager em;
    @Override
    public ExpertiseJuridiqueEntity selectExpertiseJuridiqueFromSiret(int siretStartup) throws StartUpNotFoundException {
        StartUpEntity startUpEntity = em.find(StartUpEntity.class,siretStartup);
        ExpertiseJuridiqueEntity expertiseJuridiqueEntity = (ExpertiseJuridiqueEntity)
                em.createQuery(
                        "SELECT c FROM ExpertiseJuridiqueEntity c " +
                                "WHERE c.siretStartUp = :startUpEntity")
                        .setParameter("startUpEntity", startUpEntity)
                        .getSingleResult();
        return expertiseJuridiqueEntity;
    }
    @Override
    public ExpertiseJuridiqueEntity findById(Integer id) {
        return em.find(ExpertiseJuridiqueEntity.class, id);
    }

    @Override
    public void registerExpertiseJuridique(ExpertiseJuridiqueDTO expertiseJuridique){
        ExpertiseJuridiqueEntity expertiseJuridiqueEntity = new ExpertiseJuridiqueEntity() ;
        expertiseJuridiqueEntity.setPrestataireJuridique(em.find(PrestataireJuridiqueEntity.class, expertiseJuridique.siretPrestataireJuridique()));
        expertiseJuridiqueEntity.setNombrePartExpertise(expertiseJuridique.nombrePartExpertise());
        expertiseJuridiqueEntity.setPrixActuelPartExpertise(expertiseJuridique.prixActuelPartExpertise());
        em.persist(expertiseJuridiqueEntity);
    }
}
