package fr.pantheonsorbonne.ufr27.miage.dto;

public record OnePager(
        int idOnePager,
        ExpertiseJuridiqueDTO expertiseJuridique,
        ExpertiseFinanciereDTO expertiseFinancière,
        String domaine) {
}