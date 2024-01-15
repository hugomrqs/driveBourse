package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;

public interface BusinessModelService {
    void registerBusinessModel(BusinessModelDTO businessModel) ;
    void registerContratJuridiqueBM(ContratJuridiqueBMDTO contratJuridiqueBMDTO) ;
}
