package fr.pantheonsorbonne.ufr27.miage.dto;

public record ContratJuridiqueBMDTO(
        Integer contratJuridiqueBM,
     Boolean tasvee,
     Boolean startUp,
     Integer pourcentageComissionTasvee,
     String siretTasvee,
     String siretStartUp,
     Integer idBusinessModel // Utilisation de l'ID BusinessModel au lieu de l'objet complet

    ){
}
