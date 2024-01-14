package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class PropositionDAOImpl implements PropositionDAO{

    @Inject
    EntityManager em;

    @Override
    public void createNewProposition(PropositionDTO p) {
        Proposition propo = new Proposition();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(p.siretFond());
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(false);
        em.persist(propo);
    }

    @Override
    public void createAcceptedProposition(PropositionDTO p) {
        Proposition propo = new Proposition();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(p.siretFond());
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(true);
        em.persist(propo);
    }
}
