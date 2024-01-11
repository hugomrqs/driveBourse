package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;
import jakarta.transaction.Transactional;

public interface BusinessModelService {

    int quantificationLeveeDeFonds(BilanComptable bilanComptable, int argentLevee);

    int quantificationParts(StatutEntity statuts, int partCedee);



    // Méthode pour envoyer le modèle d'affaires
  //  public void SendBusinessModel(BusinessModel businessModel, StartUp startUp);


    BusinessModel CreateBusinessModel(BusinessModel bm,int argentLeveeXpTasvee, int partCedeeXpTasvee);

    @Transactional
    void SendBusinessModel(BusinessModel businessModel, int argentLeveeXpTasvee, int partCedeeXpTasvee);

    void useOfferForm(OfferForm offerForm);

}
