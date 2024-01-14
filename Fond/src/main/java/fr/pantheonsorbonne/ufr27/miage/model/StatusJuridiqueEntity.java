package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class StatusJuridiqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDStatus", nullable = false)
    private Integer IDStatus;

    @Column(name = "nombrePart", nullable = false, length = 45)
    private Integer nbParts;
    @Column(name = "prixPartActuel", nullable = false, length = 45)
    private Integer prixPartActuel;
    /* prix d'une part à l'unité ?*/


}
