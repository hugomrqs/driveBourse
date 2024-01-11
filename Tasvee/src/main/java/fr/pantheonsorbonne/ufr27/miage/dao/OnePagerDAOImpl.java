package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped

public class OnePagerDAOImpl implements OnePagerDAO{
    @PersistenceContext(name ="mysql")
    EntityManager em;

    @Inject
    private StartUpDAO startUpDAO;

    @Override
    @Transactional
    public void createOnePager(StartUpEntity startUp,
                               ExpertiseJuridique expertiseJuridique,
                               ExpertiseFinanciere expertiseFinanciere)
            throws StartUpNotFoundException {
        OnePager onePager = new OnePager(startUp,expertiseJuridique,expertiseFinanciere);
        em.persist(onePager);
    }

    @Override
    public OnePager selectOnePagerByIdStartUp(int siretStartup) throws OnePagerNotFoundException {
        try {
            OnePager onePager = (OnePager) em.createQuery(
                            "SELECT c FROM OnePager c WHERE c.siretStartUp.siretStartUp = :siretStartup")
                    .setParameter("siretStartup",siretStartup)
                    .getSingleResult();
            return onePager;
        } catch (NoResultException e) {
            throw new OnePagerNotFoundException(siretStartup);

        }
    }
}

