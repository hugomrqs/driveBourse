package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;

public interface BusinessModelDAO {
    BusinessModelEntity getBusinessModel(Integer idBusinessModel);

    BusinessModelEntity createBusinessModel(Integer idSiretStartup, Integer ArgentLeveeXpTasvee, Integer PartCedeeXpTasvee) ;

    void addSignature(ContratJuridiqueBM contratJuridiqueBM);
}
