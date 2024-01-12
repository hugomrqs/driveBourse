package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class StartUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SiretStartUp", nullable = false)
    private Integer SiretStartUp;

    @JoinColumn(name = "idVenue", nullable = false)
    private Integer lienSiteWeb;

    @JoinColumn(name = "IDBilanComptable", nullable = false)
    private Integer IDBilanComptable;

    @JoinColumn(name = "IDStatus", nullable = false)
    private Integer IDStatus;

    @JoinColumn(name = "IDCVDirigeant", nullable = false)
    private Integer IDCVDirigeant;

    @JoinColumn(name = "IDBusinessmodel", nullable = false)
    private Integer IDBusinessmodel;

    @Column(name = "IDOnePager", nullable = false, length = 45)
    private Integer IDOnePager;

    @Column(name = "dateOfferForm", nullable = false, length = 45)
    private Date dateOfferForm;

    @Column(name = "secteur", nullable = false, length = 45)
    private String secteur;

    @Column(name = "numeroDeLevee", nullable = false, length = 45)
    private Integer numeroDeLevee;

    @Column(name = "IBAN", nullable = false, length = 45)
    private String IBAN;

    @Column(name = "argentLevee", nullable = false, length = 45)
    private Integer argentLevee;

    @Column(name = "partCede", nullable = false, length = 45)
    private Integer partCede;

}
