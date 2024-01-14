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
    private PrestataireFinancierEntity prestataireFinancierEntity;

    @OneToOne
    @JoinColumn(name = "siretStartUp", referencedColumnName = "siretStartUp")
    private StartUpEntity siretStartUp;


    public ExpertiseFinanciereEntity() {
    }

    public ExpertiseFinanciereEntity(Double bfrExpert, Double margeBrutExpert, PrestataireFinancierEntity prestataireFinancierEntity) {
        this.bfrExpert = bfrExpert;
        this.margeBrutExpert = margeBrutExpert;
        this.prestataireFinancierEntity = prestataireFinancierEntity;
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

    public PrestataireFinancierEntity getPrestataireFinancier() {
        return prestataireFinancierEntity;
    }

    public void setPrestataireFinancier(PrestataireFinancierEntity prestataireFinancierEntity) {
        this.prestataireFinancierEntity = prestataireFinancierEntity;
    }

    public StartUpEntity getSiretStartUp() {
        return siretStartUp;
    }

    public void setSiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }
}
