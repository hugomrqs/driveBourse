package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratTripartiteFinalEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ContratDAOImpl implements ContratDAO{

    @Inject
    EntityManager em;

    @Override
    public void insertContratTripartiteFinal(NDADTOCommercialisationDTO nda){
        PropositionEntity prop = new PropositionEntity(
                nda.getSiretFond(),
                nda.getSiretEntreprise(),
                nda.getSujet().leveeDeFondsFinale(),
                nda.getSujet().pourcentagePartFinale(),
                nda.getSujet().etatProposition()
        );
        ContratTripartiteFinalEntity contrat = new ContratTripartiteFinalEntity(
                true,
                nda.isSignatureFonds(),
                nda.isSignatureEntreprise(),
                prop);
        em.persist(contrat);

   }


    private PropositionEntity findByIdPropositionFinale(Integer idProp){
        PropositionEntity pf = (PropositionEntity) em.createQuery("Select pf from PropositionEntity pf where pf.idPropositionFinale =:idProp").setParameter("idProp", idProp).getSingleResult();
        return pf;
    }


}
