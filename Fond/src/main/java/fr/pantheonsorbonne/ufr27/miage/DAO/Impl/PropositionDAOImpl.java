package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropositionDAOImpl implements PropositionDAO {


    @Override
    public void createNewProposition(PropositionDTO p) {
        Proposition propo = new Proposition();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(p.siretFond());
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(false);
    }

    @Override
    public void createAcceptedProposition(PropositionDTO p) {
        Proposition propo = new Proposition();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(p.siretFond());
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(true);
    }
}
