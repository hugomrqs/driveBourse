package fr.pantheonsorbonne.ufr27.miage.java.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ExpertiseJuridique")
public class ExpertiseJuridique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseJuridique")
    private Integer idExpertiseJuridique;

    @Column(name = "nombrePartExpertise")
    private Integer nombrePartExpertise;

    @ManyToOne
    @JoinColumn(name = "SiretPrestataireJuridique", referencedColumnName = "SiretPrestataireJuridique")
    private PrestataireJuridique prestataireJuridique;

    public ExpertiseJuridique() {
    }

    public ExpertiseJuridique(Integer nombrePartExpertise, PrestataireJuridique prestataireJuridique) {
        this.nombrePartExpertise = nombrePartExpertise;
        this.prestataireJuridique = prestataireJuridique;
    }

    // Getters et Setters

    public Integer getIdExpertiseJuridique() {
        return idExpertiseJuridique;
    }

    public void setIdExpertiseJuridique(Integer idExpertiseJuridique) {
        this.idExpertiseJuridique = idExpertiseJuridique;
    }

    public Integer getNombrePartExpertise() {
        return nombrePartExpertise;
    }

    public void setNombrePartExpertise(Integer nombrePartExpertise) {
        this.nombrePartExpertise = nombrePartExpertise;
    }

    public PrestataireJuridique getPrestataireJuridique() {
        return prestataireJuridique;
    }

    public void setPrestataireJuridique(PrestataireJuridique prestataireJuridique) {
        this.prestataireJuridique = prestataireJuridique;
    }
}
