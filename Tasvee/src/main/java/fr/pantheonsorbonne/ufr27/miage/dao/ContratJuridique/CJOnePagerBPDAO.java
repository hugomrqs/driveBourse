package fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridique;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;

public interface CJOnePagerBPDAO {

     ContratJuridiqueOnePagerPourBP getContratJuridiqueOnePagerPourBP(int contratJuridiqueBMId);

   void addContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP);

    void updateContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP);

     void deleteContratJuridiqueOnePagerPourBP(int contratJuridiqueBMId);

}
