package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;

public interface OfferFormService {
    void saveOfferForm(OfferForm offerForm) ;

    boolean isOfferAccepted(OfferForm offerForm) ;
}
