package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciereEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PrestataireFinancier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ExpertiseFinanciereDAOImpl implements ExpertiseFinanciereDAO{
    @Inject
    EntityManager em;

    @Override
    public void registerExpertiseFinanciere(ExpertiseFinanciere expertiseFinanciere) {
        ExpertiseFinanciereEntity expertiseFinanciereEntity = new ExpertiseFinanciereEntity() ;
        expertiseFinanciereEntity.setPrestataireFinancier(em.find(PrestataireFinancier.class, expertiseFinanciere.siretPrestataireFinancier()));
        expertiseFinanciereEntity.setBFRExpert(expertiseFinanciere.bfrExpert());
        expertiseFinanciereEntity.setMargeBrutExpert(expertiseFinanciere.margeBrutExpert());
        em.persist(expertiseFinanciereEntity);
    }
}
