package fr.pantheonsorbonne.ufr27.miage.dto;

public record ContratJuridiqueOnePagerPourBPRecordDTO(
        Integer contratJuridiqueBM,
        Boolean tasvee,Boolean fonds,
        String siretTasvee,
        Fond siretFonds,
        OnePager idOnPager) {       }
