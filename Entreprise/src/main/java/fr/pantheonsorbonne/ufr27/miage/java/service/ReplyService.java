package fr.pantheonsorbonne.ufr27.miage.java.service;

import fr.pantheonsorbonne.ufr27.miage.java.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.java.model.ContratJuridiqueOnePagerPourBP;

public interface ReplyService {

    Boolean signeCJOnePagerBP(ContratJuridiqueOnePagerPourBP cj);

    void  addBM(BusinessModel bm);

}
