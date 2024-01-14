package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.PropositionGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import jakarta.inject.Inject;

public class PropositionServiceImpl implements PropositionService{

    @Inject
    PropositionGateway pg;

    @Inject
    PropositionDAO propositionDao;

    @Inject
    ContratDAO contratDAO;

    public void challengeProposal(PropositionDTO prop){
        try {
            if(prop.pourcentagePartFinale() > 20 || prop.leveeDeFondsFinale() < 500 ){
                System.out.println("PROPOSITION REFUSÉ");
                //FAIRE LA LOGIQUE METIER EN MULTIPLIANT LES NOMBRES LEVEE DE FOND ET POURC PART
                Integer newLeveeDeFond = prop.leveeDeFondsFinale();
                Integer newPourcPart = prop.pourcentagePartFinale();
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                        newLeveeDeFond,
                        newPourcPart,
                        prop.siretFond(),
                        false );
                pg.sendProposal(response);
                propositionDao.createNewProposition(prop);
            }else{
                System.out.println("PROPOSITION ACCEPTÉ");
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                        prop.leveeDeFondsFinale(),
                        prop.pourcentagePartFinale(),
                        prop.siretFond(),
                        true );
                propositionDao.createAcceptedProposition(response);
                pg.sendProposal(response);
                NDADTOCommercialisationDTO contrTripartite = createNDACom(prop);
                pg.sendContratTripartite(contrTripartite);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    };

    public void addLastProposal(PropositionDTO prop){
        try {
            propositionDao.createAcceptedProposition(prop);
            System.out.println("PropositionAccepté");
            NDADTOCommercialisationDTO contrTripartite = createNDACom(prop);
            pg.sendContratTripartite(contrTripartite);
        }catch (Exception e){
            System.out.println(e);
        }
    };

    public void insertNDA(NDADTOCommercialisationDTO nda){
        try {
            if(nda.isSignatureEntreprise() && nda.isSignatureFonds()){
                contratDAO.insertContratTripartiteFinal(nda);
            }else{
                pg.sendContratTripartite(nda);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    };


    private NDADTOCommercialisationDTO createNDACom(PropositionDTO prop){
        NDADTOCommercialisationDTO contratTripartite = new NDADTOCommercialisationDTO(prop,true,false,false);
        return contratTripartite;
    }
}
