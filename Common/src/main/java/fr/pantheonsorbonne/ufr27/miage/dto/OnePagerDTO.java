package fr.pantheonsorbonne.ufr27.miage.dto;

public record OnePagerDTO(
        ExpertiseJuridiqueDTO expertiseJuridiqueDTO,
        ExpertiseFinanciereDTO expertiseFinanciere,
        int siretEntreprise) {
}