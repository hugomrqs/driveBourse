package fr.pantheonsorbonne.ufr27.miage.service.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.camel.MessagingGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PropositionServiceImpl implements PropositionService {

    @Inject
    PropositionDAO propositionDao;

    @Inject
    MessagingGateway mg;


    public boolean challengeProposal(PropositionDTO prop){
        try {
            Proposition propo = new Proposition();
            propo.setLeveeDeFonds(prop.leveeDeFondsFinale());
            propo.setPourcentagePart(prop.pourcentagePartFinale());
            propo.setEtatProposition(false);
            if(prop.pourcentagePartFinale() > 20 || prop.leveeDeFondsFinale() < 100 ){
                System.out.println("PROPOSITION REFUSÉ");

                return false;
            }else{
                System.out.println("PROPOSITION ACCEPTÉ");
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    };

    public void addLastProposal(PropositionDTO prop){
        try {
            Proposition propo = new Proposition();
            propo.setIDProposition(prop.idProposition());
            propo.setSiretFonds(prop.siretFond());
            propo.setLeveeDeFonds(prop.leveeDeFondsFinale());
            propo.setPourcentagePart(prop.pourcentagePartFinale());
            propo.setEtatProposition(true);

            System.out.println("PropositionAccepté");

        }catch (Exception e){
            System.out.println(e);
        }
    };

}
