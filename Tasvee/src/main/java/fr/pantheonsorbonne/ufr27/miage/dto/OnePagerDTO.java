package fr.pantheonsorbonne.ufr27.miage.dto;

public record OnePagerDTO(
        int idOnePager,
        ExpertiseJuridiqueDTO expertiseJuridique,
        ExpertiseFinanciereDTO expertiseFinanciere,
        String domaine) {
}