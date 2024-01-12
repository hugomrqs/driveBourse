package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ExpertiseFinanciere")
public class ExpertiseFinanciereEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExpertiseFinanciere")
    private Integer idExpertiseFinanciere;

    @Column(name = "BFRExpert")
    private Double bfrExpert;

    @Column(name = "margeBrutExpert")
    private Double margeBrutExpert;

    @ManyToOne
    @JoinColumn(name = "SiretPrestataireFinancier", referencedColumnName = "SiretPrestataireFinancier")
    private PrestataireFinancier prestataireFinancier;

    public ExpertiseFinanciereEntity() {
    }

    public ExpertiseFinanciereEntity(Double bfrExpert, Double margeBrutExpert, PrestataireFinancier prestataireFinancier) {
        this.bfrExpert = bfrExpert;
        this.margeBrutExpert = margeBrutExpert;
        this.prestataireFinancier = prestataireFinancier;
    }

    public Integer getIdExpertiseFinanciere() {
        return idExpertiseFinanciere;
    }

    public void setIdExpertiseFinanciere(Integer idExpertiseFinanciere) {
        this.idExpertiseFinanciere = idExpertiseFinanciere;
    }

    public Double getBFRExpert() {
        return bfrExpert;
    }

    public void setBFRExpert(Double bfrExpert) {
        this.bfrExpert = bfrExpert;
    }

    public Double getMargeBrutExpert() {
        return margeBrutExpert;
    }

    public void setMargeBrutExpert(Double margeBrutExpert) {
        this.margeBrutExpert = margeBrutExpert;
    }

    public PrestataireFinancier getPrestataireFinancier() {
        return prestataireFinancier;
    }

    public void setPrestataireFinancier(PrestataireFinancier prestataireFinancier) {
        this.prestataireFinancier = prestataireFinancier;
    }
}
