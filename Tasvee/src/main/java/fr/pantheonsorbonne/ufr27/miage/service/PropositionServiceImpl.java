package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.PropositionGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PropositionServiceImpl implements PropositionService{

    @Inject
    PropositionGateway pg;

    @Inject
    PropositionDAO propositionDao;

    @Inject
    ContratDAO contratDAO;


    @Override
    public void challengeProposal(PropositionDTO prop){
        try {
            if(prop.pourcentagePartFinale() > 20 || prop.leveeDeFondsFinale() < 10000){
                System.out.println("PROPOSITION REFUSÉ");
                Integer newLeveeDeFond = Double.valueOf((int)prop.leveeDeFondsFinale()*1.2).intValue();
                Integer newPourcPart = Double.valueOf((int)prop.pourcentagePartFinale()*1.3).intValue();
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                        newLeveeDeFond,
                        newPourcPart,
                        prop.siretFond(),
                        prop.siretStartUp(),
                        false );
                propositionDao.createProposition(prop);
                pg.sendProposal(response);
            }else{
                System.out.println("PROPOSITION ACCEPTÉ");
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                        prop.leveeDeFondsFinale(),
                        prop.pourcentagePartFinale(),
                        prop.siretFond(),
                        prop.siretStartUp(),
                        true );
                propositionDao.createProposition(response);
                pg.sendProposal(response);
                NDADTOCommercialisationDTO contrTripartite = createNDACom(prop);
                pg.sendContratTripartite(contrTripartite);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    };

    @Override
    public void addLastProposal(PropositionDTO prop){
        try {
            propositionDao.createProposition(prop);
            System.out.println("PropositionAccepté");
            NDADTOCommercialisationDTO contrTripartite = createNDACom(prop);
            pg.sendContratTripartite(contrTripartite);
        }catch (Exception e){
            System.out.println(e);
        }
    };

    @Override
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
        return new NDADTOCommercialisationDTO(prop,true,false,false, prop.siretStartUp(),prop.siretFond());
    }
}
