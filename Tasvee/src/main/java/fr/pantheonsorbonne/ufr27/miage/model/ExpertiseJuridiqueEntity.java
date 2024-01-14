package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ExpertiseJuridique")
public class ExpertiseJuridiqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseJuridique")
    private Integer idExpertiseJuridique;

    @Column(name = "nombrePartExpertise")
    private Integer nombrePartExpertise;

    @Column(name = "prixActuelPartExpertise")
    private Integer prixActuelPartExpertise;

    @ManyToOne
    @JoinColumn(name = "SiretPrestataireJuridique", referencedColumnName = "SiretPrestataireJuridique")
    private PrestataireJuridique prestataireJuridique;

    public ExpertiseJuridiqueEntity(Integer nombrePartExpertise, PrestataireJuridique prestataireJuridique, Integer prixActuelPartExpertise,) {
        this.nombrePartExpertise = nombrePartExpertise;
        this.prixActuelPartExpertise = prixActuelPartExpertise;
        this.prestataireJuridique = prestataireJuridique;
    }

    public ExpertiseJuridiqueEntity() {

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

    public Integer getPrixActuelPartExpertise() {
        return prixActuelPartExpertise;
    }

    public void setPrixActuelPartExpertise(Integer prixActuelPartExpertise) {
        this.prixActuelPartExpertise = prixActuelPartExpertise;
    }

    public PrestataireJuridique getPrestataireJuridique() {
        return prestataireJuridique;
    }

    public void setPrestataireJuridique(PrestataireJuridique prestataireJuridique) {
        this.prestataireJuridique = prestataireJuridique;
    }
}
