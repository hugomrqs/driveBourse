package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ContratTripartiteFinalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM", nullable = false)
    private Integer ContratJuridiqueBM;

    @OneToOne
    @JoinColumn(name = "PropositionFinale", nullable = false)
    private PropositionEntity PropositionFinale;

    @Column(name = "TASVEE", nullable = false)
    private boolean TASVEE;

    @Column(name = "siretTasvee", nullable = false)
    private Integer siretTasvee;

    @Column(name = "fonds", nullable = false)
    private boolean fonds;

    @Column(name = "siretFond", nullable = false)
    private Integer siretFond;

    @Column(name = "startUp", nullable = false)
    private boolean startUp;

    @Column(name = "siretStartUp", nullable = false)
    private Integer siretStartUp;

    // Constructeur
    public ContratTripartiteFinalEntity(
            PropositionEntity PropositionFinale,
            boolean TASVEE,
            Integer siretTasvee,
            boolean fonds,
            Integer siretFond,
            boolean startUp,
            Integer siretStartUp
    ) {
        this.PropositionFinale = PropositionFinale;
        this.TASVEE = TASVEE;
        this.siretTasvee = siretTasvee;
        this.fonds = fonds;
        this.siretFond = siretFond;
        this.startUp = startUp;
        this.siretStartUp = siretStartUp;
    }

    public ContratTripartiteFinalEntity() {

    }

    // Getters et setters
    public Integer getContratJuridiqueBM() {
        return ContratJuridiqueBM;
    }

    public void setContratJuridiqueBM(Integer contratJuridiqueBM) {
        ContratJuridiqueBM = contratJuridiqueBM;
    }

    public PropositionEntity getPropositionFinale() {
        return PropositionFinale;
    }

    public void setPropositionFinale(PropositionEntity propositionFinale) {
        PropositionFinale = propositionFinale;
    }

    public boolean getTasvee() {
        return TASVEE;
    }

    public void setTASVEE(boolean TASVEE) {
        this.TASVEE = TASVEE;
    }

    public Integer getSiretTasvee() {
        return siretTasvee;
    }

    public void setSiretTasvee(Integer siretTasvee) {
        this.siretTasvee = siretTasvee;
    }

    public boolean getFonds() {
        return fonds;
    }

    public void setFonds(boolean fonds) {
        this.fonds = fonds;
    }

    public Integer getSiretFond() {
        return siretFond;
    }

    public void setSiretFond(Integer siretFond) {
        this.siretFond = siretFond;
    }

    public boolean getStartUp() {
        return startUp;
    }

    public void setStartUp(boolean startUp) {
        this.startUp = startUp;
    }

    public Integer getSiretStartUp() {
        return siretStartUp;
    }

    public void setSiretStartUp(Integer siretStartUp) {
        this.siretStartUp = siretStartUp;
    }
}
