package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.model.Fond;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;

public interface CJOnePagerBPService {
    public default ContratJuridiqueOnePagerPourBP CreateContratJuridiqueOnePagerPourBP(OnePager siretStartup, Fond fund, Boolean engagement) {
        // Implémentez la logique ici
        return null;
    }

    public default void SendContratJuridiqueOnePagerPourBP() {
        // Implémentez la logique ici
    }

    public default void RegisterContratJuridiqueOnePagerPourBP_SigneFromFond(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP) {
        // Implémentez la logique ici
    }
}
