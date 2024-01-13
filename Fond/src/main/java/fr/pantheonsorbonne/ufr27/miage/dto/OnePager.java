package fr.pantheonsorbonne.ufr27.miage.dto;

public record OnePager(
        ExpertiseJuridiqueDTO expertiseJuridique,
        ExpertiseFinanciereDTO expertiseFinanciere,
        String siretEntreprise) {
}