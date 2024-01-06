package fr.pantheonsorbonne.ufr27.miage.DAO;

import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import jakarta.transaction.Transactional;

public interface BusinessModelDAO {
    @Transactional
    BusinessModel getBusinessModel(int businessModelId);


    @Transactional
    void addBusinessModel(OfferForm of);

}
