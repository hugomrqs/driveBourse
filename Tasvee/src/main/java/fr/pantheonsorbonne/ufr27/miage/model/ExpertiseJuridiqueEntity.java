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
    private PrestataireJuridiqueEntity prestataireJuridiqueEntity;

    @ManyToOne
    @JoinColumn(name = "SiretStartUp", referencedColumnName = "SiretStartUp")
    private StartUpEntity siretStartUp;


    public ExpertiseJuridiqueEntity(Integer nombrePartExpertise, PrestataireJuridiqueEntity prestataireJuridiqueEntity, Integer prixActuelPartExpertise) {
        this.nombrePartExpertise = nombrePartExpertise;
        this.prixActuelPartExpertise = prixActuelPartExpertise;
        this.prestataireJuridiqueEntity = prestataireJuridiqueEntity;
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

    public PrestataireJuridiqueEntity getPrestataireJuridique() {
        return prestataireJuridiqueEntity;
    }

    public void setPrestataireJuridique(PrestataireJuridiqueEntity prestataireJuridiqueEntity) {
        this.prestataireJuridiqueEntity = prestataireJuridiqueEntity;
    }

    public StartUpEntity getSiretStartUp() {
        return siretStartUp;
    }

    public void setSiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }
}
