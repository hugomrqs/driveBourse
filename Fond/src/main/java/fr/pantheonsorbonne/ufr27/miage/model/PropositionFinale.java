package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;


public class PropositionFinale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM", nullable = false)
    private Integer ContratJuridiqueBM;

    @JoinColumn(name = "SiretFonds", nullable = false)
    private Integer SiretFonds;

    @Column (name = "leveeDeFondsFinale", nullable = false)
    private String leveeDeFondsFinale;


    @Column(name = "pourcentagePartFinale", nullable = false)
    private String pourcentagePartFinale;
}
