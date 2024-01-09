package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.StartUp;
import fr.pantheonsorbonne.ufr27.miage.model.Statut;

public interface BusinessModelService {

    int quantificationLeveeDeFonds(BilanComptable bilanComptable, int argentLevee);

    int quantificationParts(Statut statuts, int partCedee);


    BusinessModel CreateBusinessModel(int argentLeveeXpTasvee, int partCedeeXpTasvee, StartUp startUp);

    // Méthode pour envoyer le modèle d'affaires
  //  public void SendBusinessModel(BusinessModel businessModel, StartUp startUp);
     void SendBusinessModel(BusinessModel businessModel);


    void useOfferForm(OfferForm offerForm);

}
