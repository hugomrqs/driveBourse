package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

public class ExpertiseFinanciere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseFinanciere", nullable = false)
    private Integer IDExpertiseFinanciere;

    @JoinColumn(name = "SiretPrestataireFinancier", nullable = false)
    private Integer SiretPrestataireFinancier;

    @Column(name = "BFRExpert", nullable = false, length = 45)
    private Integer BFRExpert;

    @Column(name = "margeBrutExpert", nullable = false, length = 45)
    private Integer margeBrutExpert;

    @Column(name = "mailPrestaJuridique", nullable = false, length = 45)
    private Integer mailPrestaJuridique;


}
