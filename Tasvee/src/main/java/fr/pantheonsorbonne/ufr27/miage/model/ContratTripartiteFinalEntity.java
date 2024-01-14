package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ContratTripartiteFinal")
public class ContratTripartiteFinalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDContratTripartiteFinal")
    private Integer IDContratTripartiteFinal;

    @OneToOne
    @JoinColumn(name = "contratTripartite")
    private ContratTripartiteFinalEntity contratTripartiteFinalEntity;

    @Column(name = "tasvee")
    private Boolean tasvee;

    @Column(name = "fonds")
    private Boolean fonds;

    @Column(name = "startUp")
    private Boolean startUp;

    @ManyToOne
    @JoinColumn(name = "PropositionFinaleId", referencedColumnName = "IdPropositionFinale")
    private PropositionFinaleEntity propositionFinaleEntity;

    // Constructeur
    public ContratTripartiteFinalEntity(Boolean tasvee, Boolean fonds, Boolean startUp, PropositionFinaleEntity propositionFinaleEntity) {
        this.tasvee = tasvee;
        this.fonds = fonds;
        this.startUp = startUp;
        this.propositionFinaleEntity = propositionFinaleEntity;
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

    public PropositionFinaleEntity getPropositionFinaleEntity() {
        return propositionFinaleEntity;
    }

    public void setPropositionFinaleEntity(PropositionFinaleEntity propositionFinaleEntity) {
        this.propositionFinaleEntity = propositionFinaleEntity;
    }

    // Getter
    public ContratTripartiteFinalEntity getContratTripartiteFinalEntity() {
        return contratTripartiteFinalEntity;
    }

    // Setter
    public void setContratTripartiteFinalEntity(ContratTripartiteFinalEntity contratTripartiteFinalEntity) {
        this.contratTripartiteFinalEntity = contratTripartiteFinalEntity;
    }
}
