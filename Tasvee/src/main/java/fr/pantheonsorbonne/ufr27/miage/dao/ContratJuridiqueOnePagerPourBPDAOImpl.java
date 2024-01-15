package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBPEntity;
import fr.pantheonsorbonne.ufr27.miage.model.FondEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
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
    public void create(Boolean tasvee, Boolean fonds, int siretTasvee, FondEntity siretFonds, OnePagerEntity idOnPager) {
        ContratJuridiqueOnePagerPourBPEntity contrat = new ContratJuridiqueOnePagerPourBPEntity();
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
                "SELECT c FROM ContratJuridiqueOnePagerPourBPEntity c" +
                        " WHERE c.idOnPager.id = :onePagerId " +
                        "AND c.siretFonds.siretFonds = :siretFond " +
                        "AND c.fonds = TRUE";
        ContratJuridiqueOnePagerPourBPEntity isContratSigned = (ContratJuridiqueOnePagerPourBPEntity) em.createQuery(sqlQuery)
                .setParameter("onePagerId", onePagerId)
                .setParameter("siretFond", siretFond)
                .getSingleResult();

        return isContratSigned.getFonds(); //getFonds() = signatureduFond
    }
    @Override
    @Transactional
    public ContratJuridiqueOnePagerPourBPEntity selectContratJuridiqueOnePagerPourBPFromId(int id){
        return  em.find(ContratJuridiqueOnePagerPourBPEntity.class, id);
    }

}
