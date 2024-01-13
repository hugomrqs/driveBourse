package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;

public interface BusinessModelService {
    void registerBusinessModel(BusinessModel businessModel) ;
    void registerContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) ;
}
