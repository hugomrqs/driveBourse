package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ExpertiseJuridique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseJuridique", nullable = false)
    private Integer IDExpertiseJuridique;

    @JoinColumn(name = "SiretPrestataireJuridique", nullable = false)
    private Integer SiretPrestataireJuridique;

    @Column(name = "nombrePartExpertise", nullable = false, length = 45)
    private Integer nombrePartExpertise;

    @Column(name = "mailPrestaJuridique", nullable = false, length = 45)
    private Integer mailPrestaJuridique;
}
