package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridique;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@ApplicationScoped
public class ExpertiseJuridiqueDAOImpl implements ExpertiseJuridiqueDAO {
    @PersistenceContext(name ="mysql")
    EntityManager em;
    @Override
    public ExpertiseJuridique selectExpertiseJuridiqueFromSiret(int siretStartup) throws StartUpNotFoundException {
        return new ExpertiseJuridique();
    }
    @Override
    public ExpertiseJuridique findById(Integer id) {
        return em.find(ExpertiseJuridique.class, id);
    }
}
