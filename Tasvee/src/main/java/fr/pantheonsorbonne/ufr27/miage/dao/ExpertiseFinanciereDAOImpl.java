package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciere;
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
    public ExpertiseFinanciere selectExpertiseFinanicereFromSiret(int siretStartup) throws StartUpNotFoundException {
        return new ExpertiseFinanciere();
    }

    @Override
    public ExpertiseFinanciere findById(Integer id) {
        return em.find(ExpertiseFinanciere.class, id);
    }

    @Override
    public void registerExpertiseFinanciere(ExpertiseFinanciere expertiseFinanciere) {
        ExpertiseFinanciereEntity expertiseFinanciereEntity = new ExpertiseFinanciereEntity() ;
        expertiseFinanciereEntity.setPrestataireFinancier(em.find(PrestataireFinancier.class, expertiseFinanciere.siretPrestataireFinancier()));
        expertiseFinanciereEntity.setBFRExpert(expertiseFinanciere.bfrExpert());
        expertiseFinanciereEntity.setMargeBrutExpert(expertiseFinanciere.margeBrutExpert());
        em.persist(expertiseFinanciereEntity);
    }
}
