package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Fond;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class FondDAOImpl implements FondDAO{
    @Inject
    EntityManager em;
    public Fond selectFondBySiret(int siret){
        Fond fond = em.find(Fond.class,siret );
        return fond;
    }

}
