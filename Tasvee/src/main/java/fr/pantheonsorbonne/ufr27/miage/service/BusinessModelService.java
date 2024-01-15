package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;

public interface BusinessModelService {
    void isFormAccepted(Integer integer) ;
    void contratJuridiqueBMSigned(ContratJuridiqueBMDTO contratJuridiqueBM) ;
}
