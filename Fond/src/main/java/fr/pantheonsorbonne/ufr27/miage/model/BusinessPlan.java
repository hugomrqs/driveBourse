package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

public class BusinessPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessPlan", nullable = false)
    private Integer IDBusinessPlan;

    @JoinColumn(name = "SiretStartUp", nullable = false)
    private Integer SiretStartUp;

    @JoinColumn(name = "IDOnePager", nullable = false)
    private Integer IDOnePager;


}
