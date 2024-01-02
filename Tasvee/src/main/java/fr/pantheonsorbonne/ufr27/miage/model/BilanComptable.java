package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "BilanComptable") // obligé ?
public class BilanComptable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBilanComptable")
    private Integer idBilanComptable;

    @Column(name = "emplois")
    private Integer emplois;

    @Column(name = "ressources")
    private Integer ressources;

    @Column(name = "venteDeMarchandise")
    private Integer venteDeMarchandise;

    @Column(name = "coutDeMarchandise")
    private Integer coutDeMarchandise;


    public BilanComptable() {
    }

    public BilanComptable(
            Integer emplois,
            Integer ressources,
            Integer venteDeMarchandise,
            Integer coutDeMarchandise) {
        this.emplois=emplois;
        this.ressources=ressources;
        this.venteDeMarchandise=venteDeMarchandise;
        this.coutDeMarchandise=coutDeMarchandise;
    }

    public int getIdBilanComptable() {
        return idBilanComptable;
    }

    public void setIdBilanComptable(int idBilanComptable) {
        this.idBilanComptable = idBilanComptable;
    }

    public Integer getEmplois() {
        return emplois;
    }

    public void setEmplois(Integer emplois) {
        this.emplois = emplois;
    }

    public Integer getRessources() {
        return ressources;
    }

    public void setRessources(Integer ressources) {
        this.ressources = ressources;
    }

    public Integer getVenteDeMarchandise() {
        return venteDeMarchandise;
    }

    public void setVenteDeMarchandise(Integer venteDeMarchandise) {
        this.venteDeMarchandise = venteDeMarchandise;
    }

    public Integer getCoutDeMarchandise() {
        return coutDeMarchandise;
    }

    public void setCoutDeMarchandise(Integer coutDeMarchandise) {
        this.coutDeMarchandise = coutDeMarchandise;
    }
}

