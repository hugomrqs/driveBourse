package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;

public interface OfferFormService {
    boolean isOfferAccepted(OfferForm offerForm) ;

    void saveOfferForm(OfferForm offerForm);
}
