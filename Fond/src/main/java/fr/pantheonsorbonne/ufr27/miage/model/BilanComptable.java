package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BilanComptable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBilanComptable", nullable = false)
    private Integer IDBilanComptable;

    @Column(name = "emplois", nullable = false, length = 45)
    private Integer name;
    @Column(name = "ressources", nullable = false, length = 45)
    private Integer ressources;
    @Column(name = "venteDeMarchandise", nullable = false, length = 45)
    private String venteDeMarchandise;
    @Column(name = "coutDeMarchandise", nullable = false, length = 45)
    private String coutDeMarchandise;

}
