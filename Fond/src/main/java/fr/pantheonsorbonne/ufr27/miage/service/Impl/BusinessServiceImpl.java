package fr.pantheonsorbonne.ufr27.miage.service.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessDAO;
import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.camel.MessagingGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BusinessServiceImpl implements BusinessService {
    @Inject
    PropositionDAO propositionDao;

    @Inject
    BusinessDAO businessDAO;

    @Inject
    MessagingGateway mg;

    @Override
    public void createPropfromBP(BusinessPlanDTO bp){
        try {
            businessDAO.createNewBusinessPlan(bp);
            PropositionEntity propositionEntity = businessDAO.createRandomProposition(bp);
            PropositionDTO prop = new PropositionDTO(propositionEntity.getIDProposition(), propositionEntity.getLeveeDeFonds(), propositionEntity.getPourcentagePart(),bp.siretEntreprise(),false);
            mg.sendProposal(prop);
        }catch (Exception e){
            System.out.println(e);
        }
    };

}
