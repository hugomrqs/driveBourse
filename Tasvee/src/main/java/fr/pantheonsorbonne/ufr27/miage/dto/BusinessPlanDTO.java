package fr.pantheonsorbonne.ufr27.miage.dto;

public record BusinessPlanDTO(
        ExpertiseJuridiqueDTO expertiseJuridique,
        ExpertiseFinanciereDTO expertiseFinanciere,
        int siretEntreprise,
        int nombreEmployes,
        String serie, //serie pre-seed, seed, A,B,C,D,etc basée sur l'argent levé par la strartUp
        int partCedee
        ) {
}
