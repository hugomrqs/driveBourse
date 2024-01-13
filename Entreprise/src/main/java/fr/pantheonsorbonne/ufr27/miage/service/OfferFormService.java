package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeantDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;

public interface OfferFormService {
    void createAndSendOfferForm(BilanComptableDTO bilanComptable, StatutDTO statut, int objectLevee, Integer siretStartup,
                                     int organigramme, CvDirigeantDTO cvDirigeant, String siteWeb, String mail, String secteur);
}
