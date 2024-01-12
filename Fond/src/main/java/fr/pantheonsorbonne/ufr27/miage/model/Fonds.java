package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
public class Fonds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SiretFonds", nullable = false)
    private Integer SiretFonds;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "IBAN", nullable = false)
    private String IBAN;
}
