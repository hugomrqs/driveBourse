package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratTripartiteFinal;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionFinale;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class ContratDAOImpl implements ContratDAO{

    @Inject
    EntityManager em;

    @Override
    public void insertContratTripartiteFinal(NDADTOCommercialisationDTO nda){
        ContratTripartiteFinal contrat = new ContratTripartiteFinal(true,
                                                                    true,
                                                                    true,
                                                                            findByIdPropositionFinale(nda.getIdPropositionDTO()));
    }


    private PropositionFinale findByIdPropositionFinale(Integer idProp){
        PropositionFinale pf = (PropositionFinale) em.createQuery("Select pf from PropositionFinale pf where pf.idPropositionFinale =:idProp").setParameter("idProp", idProp).getSingleResult();
        return pf;
    }


}
