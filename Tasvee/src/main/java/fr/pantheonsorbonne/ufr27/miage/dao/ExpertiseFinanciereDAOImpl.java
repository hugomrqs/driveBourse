package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciereEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PrestataireFinancierEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;


@ApplicationScoped
public class ExpertiseFinanciereDAOImpl implements ExpertiseFinanciereDAO {
    @Inject
    @PersistenceContext(name ="mysql")
    EntityManager em;

    @Override
    public ExpertiseFinanciereEntity selectExpertiseFinanicereFromSiret(int siretStartup) throws StartUpNotFoundException {
        return em.find(StartUpEntity.class,siretStartup).getExpertiseFinanciere();
    }

    @Override
    public ExpertiseFinanciereEntity findById(Integer id) {
        return em.find(ExpertiseFinanciereEntity.class, id);
    }

    @Override
    public void registerExpertiseFinanciere(ExpertiseFinanciereDTO expertiseFinanciere) {
        ExpertiseFinanciereEntity expertiseFinanciereEntity = new ExpertiseFinanciereEntity() ;
        expertiseFinanciereEntity.setPrestataireFinancier(em.find(PrestataireFinancierEntity.class, expertiseFinanciere.siretPrestataireFinancier()));
        expertiseFinanciereEntity.setBFRExpert(expertiseFinanciere.bfrExpert());
        expertiseFinanciereEntity.setMargeBrutExpert(expertiseFinanciere.margeBrutExpert());
        em.persist(expertiseFinanciereEntity);
    }
}
