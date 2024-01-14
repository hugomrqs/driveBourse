package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dao.OfferFormDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferFormDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class OfferFormServiceImpl implements OfferFormService {

    @Inject
    OfferFormDAO offerFormDAO ;

    @Override
    public boolean isOfferAccepted(OfferFormDTO offerForm) {
        // Logique métier pour déterminer si l'offre est acceptée ou non
        // Exemple de logique : Si le montant de la levée est supérieur à un certain seuil, elle est acceptée
        System.out.println("vérification de l'offerForm en cours.");
        return offerForm.objectLevee() > 100000;
    }

    @Override
    public void saveOfferForm(OfferFormDTO offerForm) {
        offerFormDAO.registerStartUpEntity(offerForm);
        System.out.println("Suite à l'offerForm reçue, une Startup et tout ses attributs (Bilan comptable, Statut, Cv dirigeant, etc) ont bien été enregistré avec succès dans la DB.");
    }
}
