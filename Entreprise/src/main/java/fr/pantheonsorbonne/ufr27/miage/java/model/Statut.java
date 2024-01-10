package fr.pantheonsorbonne.ufr27.miage.java.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Statut")
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDStatuts")
    private Integer idStatuts;

    @Column(name = "nombrePart")
    private Integer nombrePart;

    @Column(name = "prixPartActuel")
    private Integer prixPartActuel;

    public Statut() {
    }

    public Statut( Integer nombrePart, Integer prixPartActuel) {
        this.nombrePart = nombrePart;
        this.prixPartActuel = prixPartActuel;
    }

    public int getIdStatuts() {
        return idStatuts;
    }

    public void setIdStatuts(int idStatuts) {
        this.idStatuts = idStatuts;
    }

    public Integer getNombrePart() {
        return nombrePart;
    }

    public void setNombrePart(Integer nombrePart) {
        this.nombrePart = nombrePart;
    }

    public Integer getPrixPartActuel() {
        return prixPartActuel;
    }

    public void setPrixPartActuel(Integer prixPartActuel) {
        this.prixPartActuel = prixPartActuel;
    }
}
