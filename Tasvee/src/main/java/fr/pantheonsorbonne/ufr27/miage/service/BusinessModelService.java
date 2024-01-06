package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.model.Statut;

public interface BusinessModelService {

    int quantificationLeveeDeFonds(BilanComptable bilanComptable, int argentLevee);

    int quantificationParts(Statut statuts, int partCedee);


}
