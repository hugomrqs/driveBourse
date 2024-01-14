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
            if(prop.pourcentagePartFinale() < 5 || prop.leveeDeFondsFinale() > 100000){
                System.out.println("PROPOSITION REFUSÉ");
                Integer newLeveeDeFond = Double.valueOf((int)prop.leveeDeFondsFinale()*1.3).intValue();
                Integer newPourcPart = Double.valueOf((int)prop.pourcentagePartFinale()*1.2).intValue();
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                                                            newLeveeDeFond,
                                                            newPourcPart,
                                                            prop.siretFond(),
                                                            prop.siretStartUp(),
                                                            false );
                propositionDao.createProposition(prop);
                mg.sendProposal(response);
            }else{
                System.out.println("PROPOSITION ACCEPTÉ");
                PropositionDTO response = new PropositionDTO(prop.idProposition(),
                        prop.leveeDeFondsFinale(),
                        prop.pourcentagePartFinale(),
                        prop.siretFond(),
                        prop.siretStartUp(),
                        true );
                propositionDao.createProposition(response);
                mg.sendProposal(response);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    };

    public void addLastProposal(PropositionDTO prop){
        try {
            propositionDao.createProposition(prop);
            System.out.println("PropositionAccepté");
        }catch (Exception e){
            System.out.println(e);
        }
    };

}
