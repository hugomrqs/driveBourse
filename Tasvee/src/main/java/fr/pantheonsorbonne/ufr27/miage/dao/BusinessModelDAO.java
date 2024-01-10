package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import jakarta.transaction.Transactional;

public interface BusinessModelDAO {
    BusinessModelEntity getBusinessModel(Integer idBusinessModel);

    BusinessModelEntity createBusinessModel(Integer idSiretStartup, Integer ArgentLeveeXpTasvee, Integer PartCedeeXpTasvee) ;
}
