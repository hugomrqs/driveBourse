package fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridique;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class CJOnePagerBPDAOImpl implements fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridique.CJOnePagerBPDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<ContratJuridiqueOnePagerPourBP> listAllContratJuridiqueOnePagerPourBP() {
        return em.createQuery("SELECT c FROM ContratJuridiqueOnePagerPourBP c", ContratJuridiqueOnePagerPourBP.class).getResultList();
    }

    @Transactional
    public ContratJuridiqueOnePagerPourBP getContratJuridiqueOnePagerPourBP(int contratJuridiqueBMId) {
        return em.find(ContratJuridiqueOnePagerPourBP.class, contratJuridiqueBMId);
    }

    @Transactional
    public void addContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP) {
        em.persist(contratJuridiqueOnePagerPourBP);
    }

    @Transactional
    public void updateContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP) {
        em.merge(contratJuridiqueOnePagerPourBP);
    }

    @Transactional
    public void deleteContratJuridiqueOnePagerPourBP(int contratJuridiqueBMId) {
        ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP = em.find(ContratJuridiqueOnePagerPourBP.class, contratJuridiqueBMId);
        if (contratJuridiqueOnePagerPourBP != null) {
            em.remove(contratJuridiqueOnePagerPourBP);
        }
    }
}
