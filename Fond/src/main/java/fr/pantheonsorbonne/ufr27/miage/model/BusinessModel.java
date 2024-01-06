package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BusinessModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessModel", nullable = false)
    private Integer ContratJuridiqueBM;

    @Column(name = "argentLeveeXpTasvee", nullable = false, length = 45)
    private Integer argentLeveeXpTasvee;

    @Column(name = "partCedeeXpTasvee", nullable = false, length = 45)
    private Integer partCedeeXpTasvee;


}
