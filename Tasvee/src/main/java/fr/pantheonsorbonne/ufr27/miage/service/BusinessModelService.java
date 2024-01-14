package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;

public interface BusinessModelService {
    void isFormAccepted(Integer integer) ;
    void contratJuridiqueBMSigned(ContratJuridiqueBM contratJuridiqueBM) ;
}
