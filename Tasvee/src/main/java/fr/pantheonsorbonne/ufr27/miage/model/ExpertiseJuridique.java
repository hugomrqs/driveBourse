package fr.pantheonsorbonne.ufr27.miage.model;
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

    @Column(name = "prixPartExpertise")
    private Integer prixPartExpertise;

    @ManyToOne
    @JoinColumn(name = "SiretPrestataireJuridique", referencedColumnName = "SiretPrestataireJuridique")
    private PrestataireJuridique prestataireJuridique;

    public ExpertiseJuridique() {
    }

    public ExpertiseJuridique(Integer nombrePartExpertise,Integer prixPartExpertise, PrestataireJuridique prestataireJuridique) {
        this.nombrePartExpertise = nombrePartExpertise;
        this.prixPartExpertise = prixPartExpertise;
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

    public Integer getPrixPartExpertise() {
        return prixPartExpertise;
    }

    public void setPrixPartExpertise(Integer prixPartExpertise) {
        this.prixPartExpertise = prixPartExpertise;
    }

    public PrestataireJuridique getPrestataireJuridique() {
        return prestataireJuridique;
    }

    public void setPrestataireJuridique(PrestataireJuridique prestataireJuridique) {
        this.prestataireJuridique = prestataireJuridique;
    }
}
