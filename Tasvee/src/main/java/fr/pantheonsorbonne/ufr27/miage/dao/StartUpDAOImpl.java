package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.StartUp;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped

public class StartUpDAOImpl implements StartUpDAO {
    @PersistenceContext(name ="mysql")
    EntityManager em;
    @Override
    public StartUp selectStartUp(int siretStartup) throws StartUpNotFoundException {
        try {
            StartUp startUp = (StartUp) em.createQuery(
                            "SELECT c FROM StartUp c WHERE c.siretStartUp = :siretStartup")
                    .setParameter("siretStartup", siretStartup)
                    .getSingleResult();
            return startUp;
        } catch (NoResultException e) {
                throw new StartUpNotFoundException(siretStartup);

        }
    }
}
