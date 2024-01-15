package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ContratTripartiteFinal")
public class ContratTripartiteFinalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDContratTripartiteFinal")
    private Integer IDContratTripartiteFinal;

    @Column(name = "tasvee")
    private Boolean tasvee;

    @Column(name = "fonds")
    private Boolean fonds;
    @Column(name = "startUp")
    private Boolean startUp;

    @ManyToOne
    @JoinColumn(name = "PropositionFinaleId", referencedColumnName = "IdPropositionFinale")
    private PropositionEntity propositionEntity;

    // Constructeur
    public ContratTripartiteFinalEntity(Boolean tasvee, Boolean fonds, Boolean startUp, PropositionEntity propositionEntity) {
        this.tasvee = tasvee;
        this.fonds = fonds;
        this.startUp = startUp;
        this.propositionEntity = propositionEntity;
    }

    public ContratTripartiteFinalEntity() {

    }

    // Getters et setters
    public Integer getIDContratTripartiteFinal() {
        return IDContratTripartiteFinal;
    }

    public void setIDContratTripartiteFinal(Integer IDContratTripartiteFinal) {
        this.IDContratTripartiteFinal = IDContratTripartiteFinal;
    }

    public Boolean getTasvee() {
        return tasvee;
    }

    public void setTasvee(Boolean tasvee) {
        this.tasvee = tasvee;
    }

    public Boolean getFonds() {
        return fonds;
    }

    public void setFonds(Boolean fonds) {
        this.fonds = fonds;
    }

    public Boolean getStartUp() {
        return startUp;
    }

    public void setStartUp(Boolean startUp) {
        this.startUp = startUp;
    }

    public PropositionEntity getPropositionFinaleEntity() {
        return propositionEntity;
    }

    public void setPropositionFinaleEntity(PropositionEntity propositionEntity) {
        this.propositionEntity = propositionEntity;
    }
}
