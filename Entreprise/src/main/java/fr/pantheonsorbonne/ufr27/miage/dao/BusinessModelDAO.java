package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;

public interface BusinessModelDAO {
    void registerBusinessModelInBDD(BusinessModelDTO businessModel) ;
}
