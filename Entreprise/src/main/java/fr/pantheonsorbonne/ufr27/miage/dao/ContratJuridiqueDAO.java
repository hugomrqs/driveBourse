package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import fr.pantheonsorbonne.ufr27.miage.model.CJBMEntity;

public interface ContratJuridiqueDAO {
    CJBMEntity registerContratJuridiqueBM(ContratJuridiqueBMDTO contratJuridiqueBMDTO);
    CJBMEntity sign(CJBMEntity CJBMEntity) ;
}
