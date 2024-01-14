package fr.pantheonsorbonne.ufr27.miage.dto;

public record PropositionDTO(
        Integer idProposition,
        Integer leveeDeFondsFinale,
        Integer pourcentagePartFinale,
        Integer siretTasvee,
        Integer siretStartUp,
        boolean etatProposition) {
}
