package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.FondEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PropositionDAOImpl implements PropositionDAO{

    @Inject
    EntityManager em;

    @Override
    public void createProposition(PropositionDTO p) {
        FondEntity fondEntity = em.find(FondEntity.class, p.siretFond());
        StartUpEntity startUpEntity = em.find(StartUpEntity.class, p.siretStartUp());
        PropositionEntity propo = new PropositionEntity();
        propo.setIDProposition(p.idProposition());
        propo.setSiretFonds(fondEntity);
        propo.setSiretStartUp(startUpEntity);
        propo.setLeveeDeFonds(p.leveeDeFondsFinale());
        propo.setPourcentagePart(p.pourcentagePartFinale());
        propo.setEtatProposition(p.etatProposition());
        em.persist(propo);
    }


}
