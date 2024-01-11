package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.OfferFormDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class OfferFormServiceImpl implements OfferFormService {

    @Inject
    OfferFormDAO offerFormDAO ;

    @Override
    public boolean isOfferAccepted(OfferForm offerForm) {
        // Logique métier pour déterminer si l'offre est acceptée ou non
        // Exemple de logique : Si le montant de la levée est supérieur à un certain seuil, elle est acceptée
        return offerForm.objectLevee() > 100000; // À ajuster en fonction de notre logique métier
    }

    @Override
    public void saveOfferForm(OfferForm offerForm) {
        offerFormDAO.registerStartUpEntity(offerForm);
    }
}
