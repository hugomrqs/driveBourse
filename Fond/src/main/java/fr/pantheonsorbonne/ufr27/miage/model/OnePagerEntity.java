package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class OnePagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOnePager", nullable = false)
    private Integer IDOnePager;

    @JoinColumn(name = "SiretStartUp", nullable = false)
    private Integer SiretStartUp;

    @JoinColumn(name = "IDExpertiseJuridique", nullable = false)
    private Integer IDExpertiseJuridique;

    @JoinColumn(name = "IDExpertiseFinanciere", nullable = false)
    private Integer IDExpertiseFinanciere;


}
