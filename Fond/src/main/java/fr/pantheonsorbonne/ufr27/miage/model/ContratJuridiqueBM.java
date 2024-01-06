package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

public class ContratJuridiqueBM {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM", nullable = false)
    private Integer ContratJuridiqueBM;

    @JoinColumn(name = "SiretStartUp", nullable = false)
    private boolean SiretStartUp;

    @JoinColumn(name = "IDBusinessModel", nullable = false)
    private boolean IDBusinessModel;

    @Column(name = "PourcentageComissionTasvee", nullable = false, length = 45)
    private Integer PourcentageComissionTasvee;

    @Column(name = "SiretTasvee", nullable = false, length = 45)
    private String SiretTasvee;

    @Column(name = "TASVEE", nullable = false, length = 45)
    private boolean TASVEE;

    @Column(name = "StartUp", nullable = false, length = 45)
    private boolean StartUp;
}
