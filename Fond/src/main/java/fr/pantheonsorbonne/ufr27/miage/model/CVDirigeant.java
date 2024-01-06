package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
public class CVDirigeant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCVDirigeant", nullable = false)
    private Integer IDCVDirigeant;

    @Column(name = "ecole", nullable = false, length = 45)
    private String ecole;
    @Column(name = "mainExperience", nullable = false, length = 45)
    private String mainExperience;

    @Column(name = "lienLinkedIn", nullable = false, length = 45)
    private String lienLinkedIn;

    @Column(name = "engagement", nullable = false, length = 45)
    private boolean engagement;

}
