package fr.pantheonsorbonne.ufr27.miage.dto;


import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;

public record ContratJuridiqueBM(
        Integer contratJuridiqueBM,
        Boolean tasvee,
        Boolean startUp,
        Integer pourcentageComissionTasvee,
        Integer siretTasvee,
        BusinessModelEntity idBusinessModel) {
}

