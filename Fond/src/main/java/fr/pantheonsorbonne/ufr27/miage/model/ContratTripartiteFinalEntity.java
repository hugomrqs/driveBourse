package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ContratTripartiteFinalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM", nullable = false)
    private Integer ContratJuridiqueBM;

    @OneToOne
    @JoinColumn(name = "PropositionFinale", nullable = false)
    private PropositionEntity PropositionFinale;

    @Column (name = "TASVEE", nullable = false)
    private boolean TASVEE;

    @Column (name = "siretTasvee", nullable = false)
    private Integer siretTasvee;

    @Column(name = "fonds", nullable = false)
    private boolean fonds;

    @Column (name = "siretFond", nullable = false)
    private Integer siretFond;

    @Column(name = "startUp", nullable = false)
    private boolean startUp;

    @Column (name = "siretStartUp", nullable = false)
    private Integer siretStartUp;
}
