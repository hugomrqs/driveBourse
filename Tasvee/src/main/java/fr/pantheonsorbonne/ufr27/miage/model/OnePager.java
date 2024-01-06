package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "OnePager")
public class OnePager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOnePager")
    private Integer idOnePager;

    @ManyToOne
    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private StartUpEntity siretStartUp;

    @ManyToOne
    @JoinColumn(name = "IDExpertiseJuridique", referencedColumnName = "IDExpertiseJuridique")
    private ExpertiseJuridique idExpertiseJuridique;

    @ManyToOne
    @JoinColumn(name = "IDExpertiseFinanciere", referencedColumnName = "IDExpertiseFinanciere")
    private ExpertiseFinanciere idExpertiseFinanciere;

    public OnePager() {
    }

    public OnePager(StartUpEntity siretStartUp, ExpertiseJuridique idExpertiseJuridique, ExpertiseFinanciere idExpertiseFinanciere) {
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

    public ExpertiseJuridique getIdExpertiseJuridique() {
        return idExpertiseJuridique;
    }

    public ExpertiseFinanciere getIdExpertiseFinanciere() {
        return idExpertiseFinanciere;
    }

    // Setters
    public void setIdOnePager(Integer idOnePager) {
        this.idOnePager = idOnePager;
    }

    public void setSiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public void setIdExpertiseJuridique(ExpertiseJuridique idExpertiseJuridique) {
        this.idExpertiseJuridique = idExpertiseJuridique;
    }

    public void setIdExpertiseFinanciere(ExpertiseFinanciere idExpertiseFinanciere) {
        this.idExpertiseFinanciere = idExpertiseFinanciere;
    }
}
