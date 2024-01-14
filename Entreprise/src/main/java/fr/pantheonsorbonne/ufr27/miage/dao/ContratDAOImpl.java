package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratTripartiteFinalEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import fr.pantheonsorbonne.ufr27.miage.helper.Helper;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;


public class ContratDAOImpl implements ContratDAO{

    @Inject
    EntityManager em;

    @Override
    public void insertContrat(NDADTOCommercialisationDTO nda) {
        PropositionEntity pe = new PropositionEntity(
                nda.getSiretTasvee(),
                new Helper().siret,
                nda.getSujet().leveeDeFondsFinale(),
                nda.getSujet().pourcentagePartFinale(),
                nda.getSujet().etatProposition());
        ContratTripartiteFinalEntity ndaBDD = new ContratTripartiteFinalEntity(
                pe,
            nda.isSignatureTasvee(),
                nda.getSiretTasvee(),
                nda.isSignatureFonds(),
                nda.getSujet().siretFond(),
                true,
                new Helper().siret
        );
        em.persist(ndaBDD);
    }
}
