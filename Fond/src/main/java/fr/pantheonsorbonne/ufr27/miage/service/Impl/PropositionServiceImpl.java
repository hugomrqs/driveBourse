package fr.pantheonsorbonne.ufr27.miage.service.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.inject.Inject;

public class PropositionServiceImpl implements PropositionService {

    @Inject
    PropositionDAO propositionDao;

    public boolean treatProposal(){
        try {
            /* boolean truc  = Logique métier de si on accepte la proposition*/
            propositionDao.createNewInvestment();
            return true ;
            /* return le boolean truc*/
        }catch (Exception e){
            return false;
        }
    }

}
