package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;

public interface BusinessModelDAO {
    void registerBusinessModel(BusinessModelEntity businessModelEntity) ;

    void registerContratJuridiqueBM(ContratJuridiqueBMEntity contratJuridiqueBMEntity) ;

    ContratJuridiqueBMEntity sign(ContratJuridiqueBMEntity contratJuridiqueBMEntity);
}
