package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ExpertiseJuridiqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseJuridique", nullable = false)
    private Integer IDExpertiseJuridique;

    @JoinColumn(name = "SiretPrestataireJuridique", nullable = false)
    private Integer SiretPrestataireJuridique;

    @Column(name = "nombrePartExpertise", nullable = false, length = 45)
    private Integer nombrePartExpertise;

    @Column(name = "prixPartExpertise", nullable = false, length = 45)
    private Integer prixPartExpertise;

    @Column(name = "mailPrestaJuridique", nullable = false, length = 45)
    private Integer mailPrestaJuridique;

    // Constructeur
    public ExpertiseJuridiqueEntity(Integer SiretPrestataireJuridique, Integer nombrePartExpertise, Integer mailPrestaJuridique) {
        this.SiretPrestataireJuridique = SiretPrestataireJuridique;
        this.nombrePartExpertise = nombrePartExpertise;
        this.mailPrestaJuridique = mailPrestaJuridique;
    }

    public ExpertiseJuridiqueEntity() {

    }

    // Getters et setters
    public Integer getIDExpertiseJuridique() {
        return IDExpertiseJuridique;
    }

    public void setIDExpertiseJuridique(Integer IDExpertiseJuridique) {
        this.IDExpertiseJuridique = IDExpertiseJuridique;
    }

    public Integer getSiretPrestataireJuridique() {
        return SiretPrestataireJuridique;
    }

    public void setSiretPrestataireJuridique(Integer SiretPrestataireJuridique) {
        this.SiretPrestataireJuridique = SiretPrestataireJuridique;
    }

    public Integer getNombrePartExpertise() {
        return nombrePartExpertise;
    }

    public void setNombrePartExpertise(Integer nombrePartExpertise) {
        this.nombrePartExpertise = nombrePartExpertise;
    }

    public Integer getMailPrestaJuridique() {
        return mailPrestaJuridique;
    }

    public void setMailPrestaJuridique(Integer mailPrestaJuridique) {
        this.mailPrestaJuridique = mailPrestaJuridique;
    }

    public Integer getPrixPartExpertise() {
        return prixPartExpertise;
    }

    public void setPrixPartExpertise(Integer prixPartExpertise) {
        this.prixPartExpertise = prixPartExpertise;
    }
}
