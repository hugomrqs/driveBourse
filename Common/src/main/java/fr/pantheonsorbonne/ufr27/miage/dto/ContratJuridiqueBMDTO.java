package fr.pantheonsorbonne.ufr27.miage.dto;


public record ContratJuridiqueBMDTO(
        Integer contratJuridiqueBM,
        Boolean tasvee,
        Boolean startUp,
        Integer pourcentageComissionTasvee,
        Integer siretTasvee,
        BusinessModelDTO businessModel) {
}

