package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.model.Fond;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ContratJuridiqueOnePagerPourBPDAOImpl implements ContratJuridiqueOnePagerPourBPDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Boolean tasvee, Boolean fonds, String siretTasvee, Fond siretFonds, OnePager idOnPager) {
        ContratJuridiqueOnePagerPourBP contrat = new ContratJuridiqueOnePagerPourBP();
        contrat.setTasvee(tasvee);
        contrat.setFonds(fonds);
        contrat.setSiretTasvee(siretTasvee);
        contrat.setSiretFonds(siretFonds);
        contrat.setIdOnPager(idOnPager);
        em.persist(contrat);
    }
    @Override
    public boolean isContratJuridiqueOnePagerPourBPDAOSigneByFond(int onePagerId, int siretFond){
        String sqlQuery =
                "SELECT c FROM ContratJuridiqueOnePagerPourBP c" +
                        " WHERE c.idOnPager.id = :onePagerId " +
                        "AND c.siretFonds.siretFonds = :siretFond " +
                        "AND c.fonds = TRUE";
        ContratJuridiqueOnePagerPourBP isContratSigned = (ContratJuridiqueOnePagerPourBP) em.createQuery(sqlQuery)
                .setParameter("onePagerId", onePagerId)
                .setParameter("siretFond", siretFond)
                .getSingleResult();

        return isContratSigned.getFonds(); //getFonds() = signatureduFond
    }
}
