package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "OnePager")
public class OnePager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOnePager")
    private Integer idOnePager;
    @OneToOne
    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private StartUpEntity siretStartUp;
    @ManyToOne
    @JoinColumn(name = "IDExpertiseJuridique", referencedColumnName = "IDExpertiseJuridique")
    private ExpertiseJuridiqueEntity idExpertiseJuridique;

    @ManyToOne
    @JoinColumn(name = "IDExpertiseFinanciere", referencedColumnName = "IDExpertiseFinanciere")
    private ExpertiseFinanciereEntity idExpertiseFinanciere;

    public OnePager() {
    }

    public OnePager(StartUpEntity siretStartUp, ExpertiseJuridiqueEntity idExpertiseJuridique, ExpertiseFinanciereEntity idExpertiseFinanciere) {
        this.siretStartUp = siretStartUp;
        this.idExpertiseJuridique = idExpertiseJuridique;
        this.idExpertiseFinanciere = idExpertiseFinanciere;
    }

    // Getters
    public Integer getIdOnePager() {
        return idOnePager;
    }

    public StartUpEntity getSiretStartUp() {
        return siretStartUp;
    }

    public ExpertiseJuridiqueEntity getIdExpertiseJuridique() {
        return idExpertiseJuridique;
    }

    public ExpertiseFinanciereEntity getIdExpertiseFinanciere() {
        return idExpertiseFinanciere;
    }

    // Setters
    public void setIdOnePager(Integer idOnePager) {
        this.idOnePager = idOnePager;
    }

    public void setSiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public void setIdExpertiseJuridique(ExpertiseJuridiqueEntity idExpertiseJuridique) {
        this.idExpertiseJuridique = idExpertiseJuridique;
    }

    public void setIdExpertiseFinanciere(ExpertiseFinanciereEntity idExpertiseFinanciere) {
        this.idExpertiseFinanciere = idExpertiseFinanciere;
    }
}
