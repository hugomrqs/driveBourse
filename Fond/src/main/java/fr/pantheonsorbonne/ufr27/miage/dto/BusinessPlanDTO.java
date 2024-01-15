package fr.pantheonsorbonne.ufr27.miage.dto;

public record BusinessPlanDTO(
        ExpertiseJuridiqueDTO expertiseJuridique,
        ExpertiseFinanciereDTO expertiseFinanciere,
        Integer siretEntreprise,
        OrganigrammeDTO organigrammeDTO,
        String siteWeb) {
}
