package fr.pantheonsorbonne.ufr27.miage.dto;

public record OnePagerDTO(
        ExpertiseJuridiqueDTO expertiseJuridiqueDTO,
        ExpertiseFinanciereDTO expertiseFinancière,
        int siretEntreprise) {
}