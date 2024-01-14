package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ExpertiseFinanciereEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseFinanciere", nullable = false)
    private Integer IDExpertiseFinanciere;

    @JoinColumn(name = "SiretPrestataireFinancier", nullable = false)
    private Integer SiretPrestataireFinancier;

    @Column(name = "BFRExpert", nullable = false, length = 45)
    private Integer BFRExpert;

    @Column(name = "margeBrutExpert", nullable = false, length = 45)
    private Integer margeBrutExpert;

    @Column(name = "mailPrestaJuridique", nullable = false, length = 45)
    private Integer mailPrestaJuridique;

    // Constructeur
    public ExpertiseFinanciereEntity(Integer SiretPrestataireFinancier, Integer BFRExpert, Integer margeBrutExpert, Integer mailPrestaJuridique) {
        this.SiretPrestataireFinancier = SiretPrestataireFinancier;
        this.BFRExpert = BFRExpert;
        this.margeBrutExpert = margeBrutExpert;
        this.mailPrestaJuridique = mailPrestaJuridique;
    }

    public ExpertiseFinanciereEntity() {

    }

    // Getters et setters
    public Integer getIDExpertiseFinanciere() {
        return IDExpertiseFinanciere;
    }

    public void setIDExpertiseFinanciere(Integer IDExpertiseFinanciere) {
        this.IDExpertiseFinanciere = IDExpertiseFinanciere;
    }

    public Integer getSiretPrestataireFinancier() {
        return SiretPrestataireFinancier;
    }

    public void setSiretPrestataireFinancier(Integer SiretPrestataireFinancier) {
        this.SiretPrestataireFinancier = SiretPrestataireFinancier;
    }

    public Integer getBFRExpert() {
        return BFRExpert;
    }

    public void setBFRExpert(Integer BFRExpert) {
        this.BFRExpert = BFRExpert;
    }

    public Integer getMargeBrutExpert() {
        return margeBrutExpert;
    }

    public void setMargeBrutExpert(Integer margeBrutExpert) {
        this.margeBrutExpert = margeBrutExpert;
    }

    public Integer getMailPrestaJuridique() {
        return mailPrestaJuridique;
    }

    public void setMailPrestaJuridique(Integer mailPrestaJuridique) {
        this.mailPrestaJuridique = mailPrestaJuridique;
    }
}
