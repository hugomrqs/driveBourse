package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.OfferFormDTO;

public interface OfferFormService {
    boolean isOfferAccepted(OfferFormDTO offerForm) ;

    void saveOfferForm(OfferFormDTO offerForm);
}
