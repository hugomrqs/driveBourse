package fr.pantheonsorbonne.ufr27.miage.model;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import jakarta.persistence.*;

@Entity
public class OnePagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOnePager", nullable = false)
    private Integer IDOnePager;

    @JoinColumn(name = "SiretStartUp", nullable = false)
    private Integer SiretStartUp;

    @OneToOne
    @JoinColumn(name = "ExpertiseJuridique", nullable = false)
    private ExpertiseJuridiqueEntity ExpertiseJuridique;

    @OneToOne
    @JoinColumn(name = "ExpertiseFinanciere", nullable = false)
    private ExpertiseFinanciereEntity ExpertiseFinanciere;

    public OnePagerEntity(ExpertiseFinanciereEntity expertiseFinanciere, ExpertiseJuridiqueEntity expertiseJuridique, Integer siretStartUp) {
        this.SiretStartUp = siretStartUp;
        this.ExpertiseFinanciere = expertiseFinanciere;
        this.ExpertiseJuridique = expertiseJuridique;
    }

    public OnePagerEntity() {

    }


    // Getters et setters
    public Integer getIDOnePager() {
        return IDOnePager;
    }

    public void setIDOnePager(Integer IDOnePager) {
        this.IDOnePager = IDOnePager;
    }

    public Integer getSiretStartUp() {
        return SiretStartUp;
    }

    public void setSiretStartUp(Integer SiretStartUp) {
        this.SiretStartUp = SiretStartUp;
    }

    public ExpertiseJuridiqueEntity getExpertiseJuridique() {
        return ExpertiseJuridique;
    }

    public void setIDExpertiseJuridique(ExpertiseJuridiqueEntity ExpertiseJuridique) {
        this.ExpertiseJuridique = ExpertiseJuridique;
    }

    public ExpertiseFinanciereEntity getExpertiseFinanciere() {
        return ExpertiseFinanciere;
    }

    public void setIDExpertiseFinanciere(ExpertiseFinanciereEntity ExpertiseFinanciere) {
        this.ExpertiseFinanciere = ExpertiseFinanciere;
    }
}
