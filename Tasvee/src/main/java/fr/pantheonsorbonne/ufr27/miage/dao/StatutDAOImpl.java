package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StatutDAOImpl implements StatutDAO {

    @Inject
    EntityManager em;
    @Override
    @Transactional
    public StatutEntity getStatut(int idStatut) {
        StatutEntity statutEntity = em.find(StatutEntity.class, idStatut);
        return statutEntity ;
    }
}
