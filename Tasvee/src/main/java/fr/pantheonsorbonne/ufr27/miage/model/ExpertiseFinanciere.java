package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ExpertiseFinanciere")
public class ExpertiseFinanciere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseFinanciere")
    private Integer idExpertiseFinanciere;

    @Column(name = "BFRExpert")
    private Integer bfrExpert;

    @Column(name = "margeBrutExpert")
    private Integer margeBrutExpert;

    @ManyToOne
    @JoinColumn(name = "SiretPrestataireFinancier", referencedColumnName = "SiretPrestataireFinancier")
    private PrestataireFinancier prestataireFinancier;

    public ExpertiseFinanciere() {
    }

    public ExpertiseFinanciere(Integer bfrExpert, Integer margeBrutExpert, PrestataireFinancier prestataireFinancier) {
        this.bfrExpert = bfrExpert;
        this.margeBrutExpert = margeBrutExpert;
        this.prestataireFinancier = prestataireFinancier;
    }

    // Getters et Setters

    public Integer getIdExpertiseFinanciere() {
        return idExpertiseFinanciere;
    }

    public void setIdExpertiseFinanciere(Integer idExpertiseFinanciere) {
        this.idExpertiseFinanciere = idExpertiseFinanciere;
    }

    public Integer getBFRExpert() {
        return bfrExpert;
    }

    public void setBFRExpert(Integer bfrExpert) {
        this.bfrExpert = bfrExpert;
    }

    public Integer getMargeBrutExpert() {
        return margeBrutExpert;
    }

    public void setMargeBrutExpert(Integer margeBrutExpert) {
        this.margeBrutExpert = margeBrutExpert;
    }

    public PrestataireFinancier getPrestataireFinancier() {
        return prestataireFinancier;
    }

    public void setPrestataireFinancier(PrestataireFinancier prestataireFinancier) {
        this.prestataireFinancier = prestataireFinancier;
    }
}
