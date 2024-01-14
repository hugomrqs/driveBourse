package fr.pantheonsorbonne.ufr27.miage.dto;


public record ContratJuridiqueBM(
        Integer contratJuridiqueBM,
        Boolean tasvee,
        Boolean startUp,
        Integer pourcentageComissionTasvee,
        Integer siretTasvee,
        BusinessModel BusinessModel) {
}

