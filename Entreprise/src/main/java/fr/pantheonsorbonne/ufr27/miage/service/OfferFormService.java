package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeant;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;

public interface OfferFormService {
    void createAndSendOfferForm(BilanComptable bilanComptable, Statut statut, int objectLevee, Integer siretStartup,
                                     int organigramme, CvDirigeant cvDirigeant, String siteWeb, String mail, String secteur);
}
