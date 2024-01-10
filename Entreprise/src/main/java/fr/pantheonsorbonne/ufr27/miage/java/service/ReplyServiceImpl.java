package fr.pantheonsorbonne.ufr27.miage.java.service;

import fr.pantheonsorbonne.ufr27.miage.java.DAO.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.java.DAO.CJOnePagerBPDAO;
import fr.pantheonsorbonne.ufr27.miage.java.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.java.model.ContratJuridiqueOnePagerPourBP;
import jakarta.inject.Inject;

public class ReplyServiceImpl implements ReplyService{

    @Inject
    BusinessModelDAO bmDAO;

    @Inject
    CJOnePagerBPDAO cjDAO;
    @Override
    public Boolean signeCJOnePagerBP(ContratJuridiqueOnePagerPourBP cj) {
        cjDAO.addContratJuridiqueOnePagerPourBP(cj);
        return true;
    }

    @Override
    public void addBM(BusinessModel bm) {
        bmDAO.addBusinessModel(bm);
    }
}
