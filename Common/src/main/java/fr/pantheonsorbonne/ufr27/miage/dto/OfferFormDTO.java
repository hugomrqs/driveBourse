package fr.pantheonsorbonne.ufr27.miage.dto;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeantDTO;


public record OfferFormDTO(
        BilanComptableDTO bilanComptableDTO,
        StatutDTO statutDTO,
        int objectLevee,
        Integer siretStartup,
        int organigramme,
        CvDirigeantDTO cvDirigeant,
        String siteWeb,
        String mail,
        String secteur) {
}

