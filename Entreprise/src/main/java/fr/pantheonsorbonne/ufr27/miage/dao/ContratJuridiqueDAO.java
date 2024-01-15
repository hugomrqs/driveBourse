package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import fr.pantheonsorbonne.ufr27.miage.model.CJBMEntity;

public interface ContratJuridiqueDAO {
    CJBMEntity registerContratJuridiqueBMInDB(ContratJuridiqueBMDTO contratJuridiqueBM);
    CJBMEntity sign(CJBMEntity cjbmEntity) ;
}
