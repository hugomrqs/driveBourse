package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PropositionDAOImpl implements PropositionDAO {

    @Inject
    EntityManager em;
    @Inject
    Helper helper;

    @Override
    public void createProposition(PropositionDTO p) {
        PropositionEntity propo = new PropositionEntity();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(helper.siret);
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(p.etatProposition());
        em.persist(propo);
    }

}
