package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ContratTripartiteFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM", nullable = false)
    private Integer ContratJuridiqueBM;

    @JoinColumn(name = "PropositionFinale", nullable = false)
    private Integer PropositionFinale;

    @Column (name = "TASVEE", nullable = false)
    private boolean TASVEE;

    @Column(name = "fonds", nullable = false)
    private boolean fonds;

    @Column(name = "startUp", nullable = false)
    private boolean startUp;
}
