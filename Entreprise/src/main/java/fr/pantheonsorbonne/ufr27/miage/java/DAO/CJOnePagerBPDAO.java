package fr.pantheonsorbonne.ufr27.miage.java.DAO;


import fr.pantheonsorbonne.ufr27.miage.java.model.ContratJuridiqueOnePagerPourBP;

public interface CJOnePagerBPDAO {

     ContratJuridiqueOnePagerPourBP getContratJuridiqueOnePagerPourBP(int contratJuridiqueBMId);

   void addContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP);

    void updateContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP);

     void deleteContratJuridiqueOnePagerPourBP(int contratJuridiqueBMId);

}
