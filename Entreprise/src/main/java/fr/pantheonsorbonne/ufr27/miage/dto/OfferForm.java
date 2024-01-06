package fr.pantheonsorbonne.ufr27.miage.dto;

public record OfferForm(
        BilanComptable bilanComptable,
        Statut statut,
        int objectLevee,
        Integer siretStartup,
        int organigramme,
        CvDirigeant cvDirigeant,
        String siteWeb,
        String mail,
        String secteur) {
}



