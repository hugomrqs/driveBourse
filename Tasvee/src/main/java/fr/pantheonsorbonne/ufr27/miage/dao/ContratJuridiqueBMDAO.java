package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;

public interface ContratJuridiqueBMDAO {
    ContratJuridiqueBMEntity getContratJuridiqueBM(Integer idContratJuridiqueBM);

    ContratJuridiqueBMEntity createContratJuridiqueBM(BusinessModelEntity businessModelEntity, int pourcentageComissionTasvee) ;
}
