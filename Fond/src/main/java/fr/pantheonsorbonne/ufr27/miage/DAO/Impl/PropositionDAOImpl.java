package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PropositionDAOImpl implements PropositionDAO {

    @Inject
    EntityManager em;

    @Override
    public void createNewProposition(PropositionDTO p) {
        PropositionEntity propo = new PropositionEntity();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(p.siretFond());
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(false);
        em.persist(propo);
    }

    @Override
    public void createAcceptedProposition(PropositionDTO p) {
        PropositionEntity propo = new PropositionEntity();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(p.siretFond());
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(true);
        em.persist(propo);
    }
}
