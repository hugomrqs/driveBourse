package fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridique;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class ContraJuridiqueDAOImpl implements fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridique.ContratJuridiqueDAO {
    @PersistenceContext(name = "mysql")
    private EntityManager em;

    @Transactional
    public List<ContratJuridiqueBM> listAllContratJuridiqueBM() {
        return em.createQuery("SELECT c FROM ContratJuridiqueBM c", ContratJuridiqueBM.class).getResultList();
    }

    @Transactional
    public ContratJuridiqueBM getContratJuridiqueBM(int contratJuridiqueBMId) {
        return em.find(ContratJuridiqueBM.class, contratJuridiqueBMId);
    }

    @Transactional
    public void addContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) {
        em.persist(contratJuridiqueBM);
    }

    @Transactional
    public void updateContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) {
        em.merge(contratJuridiqueBM);
    }

    @Transactional
    public void deleteContratJuridiqueBM(int contratJuridiqueBMId) {
        ContratJuridiqueBM contratJuridiqueBM = em.find(ContratJuridiqueBM.class, contratJuridiqueBMId);
        if (contratJuridiqueBM != null) {
            em.remove(contratJuridiqueBM);
        }
    }

}
