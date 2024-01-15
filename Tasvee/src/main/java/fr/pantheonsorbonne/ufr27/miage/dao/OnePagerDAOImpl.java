package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciereEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridiqueEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OnePagerDAOImpl implements OnePagerDAO{
    @PersistenceContext(name ="mysql")
    EntityManager em;

    @Override
    @Transactional
    public void createOnePager(StartUpEntity startUp,
                               ExpertiseJuridiqueEntity expertiseJuridiqueEntity,
                               ExpertiseFinanciereEntity expertiseFinanciereEntity) {
        OnePagerEntity onePagerEntity = new OnePagerEntity(startUp, expertiseJuridiqueEntity, expertiseFinanciereEntity);
        em.persist(onePagerEntity);
    }

    @Override
    public OnePagerEntity selectOnePagerByIdStartUp(int siretStartup) throws OnePagerNotFoundException {
        try {
            OnePagerEntity onePagerEntity = (OnePagerEntity) em.createQuery(
                            "SELECT c FROM OnePagerEntity c WHERE c.siretStartUp.siretStartUp = :siretStartup")
                    .setParameter("siretStartup",siretStartup)
                    .getSingleResult();
            return onePagerEntity;
        } catch (NoResultException e) {
            throw new OnePagerNotFoundException(siretStartup);

        }
    }

    @Override
    public OnePagerEntity selectOnePagerById(int idOnePager){
        OnePagerEntity onePagerEntity = em.find(OnePagerEntity.class, idOnePager);
        return onePagerEntity;
    }

}

