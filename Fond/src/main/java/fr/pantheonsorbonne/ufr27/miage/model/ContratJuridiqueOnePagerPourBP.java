package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
public class ContratJuridiqueOnePagerPourBP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM", nullable = false)
    private Integer ContratJuridiqueBM;

    @JoinColumn(name = "SiretFonds", nullable = false)
    private Integer SiretFonds;

    @JoinColumn(name = "IDOnePager", nullable = false)
    private Integer IDOnePager;

    @Column(name = "TASVEE", nullable = false)
    private boolean TASVEE;

    @Column(name = "Fonds", nullable = false)
    private boolean Fonds;

    @Column(name = "SiretTasvee", nullable = false)
    private String SiretTasvee;

}
