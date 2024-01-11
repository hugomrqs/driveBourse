package fr.pantheonsorbonne.ufr27.miage.dto;

public record OfferFormDTO(
        BilanComptableDTO bilanComptableDTO,
        StatutDTO statutDTO,
        int objectLevee,
        String siretEntreprise,
        int organigramme,
        CvDirigeantDTO cvDirigeant,
        String siteWeb) {
}

