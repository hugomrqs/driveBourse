package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.FondEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class FondDAOImpl implements FondDAO{
    @Inject
    EntityManager em;
    public FondEntity selectFondBySiret(int siret){
        FondEntity fondEntity = em.find(FondEntity.class,siret );
        return fondEntity;
    }

}
