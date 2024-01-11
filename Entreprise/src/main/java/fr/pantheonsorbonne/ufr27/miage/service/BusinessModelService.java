package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;

public interface BusinessModelService {
    void registerBusinessModel(BusinessModelEntity businessModelEntity) ;
    void registerContratJuridiqueBM(ContratJuridiqueBMEntity contratJuridiqueBMEntity) ;
}
