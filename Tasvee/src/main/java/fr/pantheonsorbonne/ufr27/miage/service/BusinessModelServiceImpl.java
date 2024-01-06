package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.model.Statut;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class BusinessModelServiceImpl implements  BusinessModelService {

    @Inject
    OfferService offerService;
    @Inject
    BusinessModelDAO bmDAO;

    @Override
    public int quantificationLeveeDeFonds(BilanComptable bilanComptable, int argentLevee) {
        // Implémentez votre logique métier ici pour quantifier la levée de fonds
        // ...
        int argentLeveeXpTasvee = 0;
        return argentLeveeXpTasvee;
    }

    @Override
    public int quantificationParts(Statut statuts, int partCedee) {
        int partCedeeXpTasvee = 0;
        return partCedeeXpTasvee;    }

}
