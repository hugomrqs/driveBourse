package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;

public interface CJOnePagerBPService {
    public default ContratJuridiqueOnePagerPourBP CreateContratJuridiqueOnePagerPourBP(OnePager siretStartup, Fond fund, Boolean engagement) {
        // Implémentez la logique ici
        return null;
    }

    public default void SendContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP cjbp) {
        // Implémentez la logique ici
    }

    public default void RegisterContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP) {
        // Implémentez la logique ici
    }
}
