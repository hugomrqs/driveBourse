package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OfferFormServiceImpl implements OfferFormService {

    @Override
    public void saveOfferForm(OfferForm offerForm) {
        // Code pour sauvegarder l'OfferForm en base de données
        // Utilisez la logique de persistance(JPA) ici
    }

    @Override
    public boolean isOfferAccepted(OfferForm offerForm) {
        // Logique métier pour déterminer si l'offre est acceptée ou non
        // Exemple de logique : Si le montant de la levée est supérieur à un certain seuil, elle est acceptée
        return offerForm.objectLevee() > 100000; // À ajuster en fonction de notre logique métier
    }
}
