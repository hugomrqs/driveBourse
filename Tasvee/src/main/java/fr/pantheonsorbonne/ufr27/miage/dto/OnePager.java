package fr.pantheonsorbonne.ufr27.miage.dto;

public record OnePager(
        int idOnePager,
        ExpertiseJuridique expertiseJuridique,
        ExpertiseFinanciere expertiseFinancière,
        String domaine) {
}