package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.StartUp;

public interface ContratJuridiqueService {
    ContratJuridiqueBM createContratJuridiqueBM(String siretTasvee, StartUp siretStartUp, BusinessModel idBusinessModel,
                                                boolean tasvee, boolean startUp, int pourcentageComissionTasvee);


    void sendContratJuridiqueBMtoStartUp(ContratJuridiqueBM contratJuridiqueBM);

    ContratJuridiqueBM registerContratJuridiqueBMSigneeFromEntrepreneur(ContratJuridiqueBM contratJuridiqueBM);
}
