package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BilanComptableDAOImpl implements BilanComptableDAO {

    @Inject
    EntityManager em;
    @Override
    @Transactional
    public BilanComptableEntity getBilanComptable(int idBilanComptable) {
        BilanComptableEntity bilanComptableEntity = em.find(BilanComptableEntity.class, idBilanComptable);
        return bilanComptableEntity ;
    }
}
