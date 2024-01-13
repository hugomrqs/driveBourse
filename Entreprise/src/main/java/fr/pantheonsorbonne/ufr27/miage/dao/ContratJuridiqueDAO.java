package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.CJBMEntity;

public interface ContratJuridiqueDAO {
    CJBMEntity registerContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM);
    CJBMEntity sign(CJBMEntity CJBMEntity) ;
}
