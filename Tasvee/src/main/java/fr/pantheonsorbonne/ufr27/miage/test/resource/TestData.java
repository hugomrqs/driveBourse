package fr.pantheonsorbonne.ufr27.miage.test.resource;

import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;
import fr.pantheonsorbonne.ufr27.miage.model.CVDirigeantEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;

public record TestData(StatutEntity statut, CVDirigeantEntity cvDirigeant, BilanComptableEntity bilanComptable) {

}