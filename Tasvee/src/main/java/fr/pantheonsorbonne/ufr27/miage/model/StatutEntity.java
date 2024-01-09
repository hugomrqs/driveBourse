package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Statut")
public class StatutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdStatut")
    private Integer idStatut;

    @Column(name = "nombrePart")
    private Integer nombrePart;

    @Column(name = "prixPartActuel")
    private Integer prixPartActuel;

    @Column(name = "strategieEntrepreneur")
    private Integer strategieEntrepreneur;

    public StatutEntity() {
    }

    public StatutEntity(Integer nombrePart, Integer prixPartActuel) {
        this.nombrePart = nombrePart;
        this.prixPartActuel = prixPartActuel;
    }

    public StatutEntity(int i, int i1, int i2) {
        this.nombrePart = i;
        this.prixPartActuel = i1;
        this.strategieEntrepreneur = i2 ;

    }

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
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

    public Integer getStrategieEntrepreneur() {
        return strategieEntrepreneur;
    }

    public void setStrategieEntrepreneur(Integer strategieEntrepreneur) { this.strategieEntrepreneur = strategieEntrepreneur; }
}
