package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridiqueEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PrestataireJuridique;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExpertiseJuridiqueDAOImpl implements ExpertiseJuridiqueDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void registerExpertiseJuridique(ExpertiseJuridique expertiseJuridique){
        ExpertiseJuridiqueEntity expertiseJuridiqueEntity = new ExpertiseJuridiqueEntity() ;
        expertiseJuridiqueEntity.setPrestataireJuridique(em.find(PrestataireJuridique.class, expertiseJuridique.siretPrestataireJuridique()));
        expertiseJuridiqueEntity.setNombrePartExpertise(expertiseJuridique.nombrePartExpertise());
        expertiseJuridiqueEntity.setPrixActuelPartExpertise(expertiseJuridique.prixActuelPartExpertise());
        em.persist(expertiseJuridiqueEntity);
    }
}
