package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratTripartiteFinalEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionFinaleEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class ContratDAOImpl implements ContratDAO{

    @Inject
    EntityManager em;

    @Override
    public void insertContratTripartiteFinal(NDADTOCommercialisationDTO nda){
        PropositionFinaleEntity prop = new PropositionFinaleEntity(
                nda.getSujet().leveeDeFondsFinale(),
                nda.getSujet().pourcentagePartFinale(),
                nda.getSiretFond()
        );
        ContratTripartiteFinalEntity contrat = new ContratTripartiteFinalEntity(
                true,
                nda.isSignatureFonds(),
                nda.isSignatureEntreprise(),
                prop);
        em.persist(contrat);

   }


    private PropositionFinaleEntity findByIdPropositionFinale(Integer idProp){
        PropositionFinaleEntity pf = (PropositionFinaleEntity) em.createQuery("Select pf from PropositionFinaleEntity pf where pf.idPropositionFinale =:idProp").setParameter("idProp", idProp).getSingleResult();
        return pf;
    }


}
