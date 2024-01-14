package fr.pantheonsorbonne.ufr27.miage.service.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.camel.MessagingGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PropositionServiceImpl implements PropositionService {

    @Inject
    PropositionDAO propositionDao;

    @Inject
    MessagingGateway mg;


    public void challengeProposal(PropositionDTO prop){
        try {
            if(prop.pourcentagePartFinale() < 10 || prop.leveeDeFondsFinale() > 1000){
                System.out.println("PROPOSITION REFUSÉ");
                Integer newLeveeDeFond = prop.leveeDeFondsFinale();
                Integer newPourcPart = prop.pourcentagePartFinale();
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                                                            newLeveeDeFond,
                                                            newPourcPart,
                                                            prop.siretFond(),
                                                            false );
                mg.sendProposal(response);
                propositionDao.createNewProposition(prop);
            }else{
                System.out.println("PROPOSITION ACCEPTÉ");
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                        prop.leveeDeFondsFinale(),
                        prop.pourcentagePartFinale(),
                        prop.siretFond(),
                        true );
                propositionDao.createAcceptedProposition(response);
                mg.sendProposal(response);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    };

    public void addLastProposal(PropositionDTO prop){
        try {
            propositionDao.createAcceptedProposition(prop);
            System.out.println("PropositionAccepté");
        }catch (Exception e){
            System.out.println(e);
        }
    };

}
