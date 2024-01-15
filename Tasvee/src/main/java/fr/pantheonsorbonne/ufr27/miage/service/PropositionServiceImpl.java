package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.PropositionGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
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
                System.out.println("La proposition a été refusé par Tasvee");
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
                System.out.println("La proposition a été accepté par Tasvee");
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
            System.out.println("La proposition de Fond a été accepté par Tasvee");
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
                System.out.println("Le contrat a été signé par Fond et Entreprise, il va donc être enrengistré en BDD");
                contratDAO.insertContratTripartiteFinal(nda);
            }else{
                if(nda.isSignatureEntreprise()){
                    pg.sendContratTripartite(nda);
                }else if(nda.isSignatureFonds()){
                    pg.sendContratTripartiteToEntreprise(nda);
                }
            }

        }catch (Exception | StartUpNotFoundException e){
            System.out.println(e);
        }
    };


    private NDADTOCommercialisationDTO createNDACom(PropositionDTO prop){
        return new NDADTOCommercialisationDTO(prop,true,false,false, prop.siretStartUp(),prop.siretFond());
    }
}
