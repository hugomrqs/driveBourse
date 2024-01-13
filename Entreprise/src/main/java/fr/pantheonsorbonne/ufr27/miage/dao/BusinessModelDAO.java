package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;

public interface BusinessModelDAO {
    void registerBusinessModel(BusinessModel businessModel) ;

    ContratJuridiqueBMEntity registerContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) ;

    ContratJuridiqueBMEntity sign(ContratJuridiqueBMEntity contratJuridiqueBMEntity);
}
