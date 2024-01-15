package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ContratTripartiteFinalEntity;
import fr.pantheonsorbonne.ufr27.miage.model.FondEntity;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ContratDAOImpl implements ContratDAO{

    @Inject
    EntityManager em;
@Inject
FondDAO fondDAO;

@Inject
StartUpDAO startUpDAO;
    @Override
    public void insertContratTripartiteFinal(NDADTOCommercialisationDTO nda) throws StartUpNotFoundException {
        FondEntity fond = fondDAO.selectFondBySiret(nda.getSiretFond());
        StartUpEntity startUp = startUpDAO.selectStartUp(nda.getSiretEntreprise());
        PropositionEntity prop = new PropositionEntity(
                fond,
                startUp,
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
