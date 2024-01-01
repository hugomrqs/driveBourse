package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ContratTripartiteFinal")
public class ContratTripartiteFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM")
    private Integer contratJuridiqueBM;

    @Column(name = "TASVEE")
    private Boolean tasvee;

    @Column(name = "fonds")
    private Boolean fonds;

    @Column(name = "startUp")
    private Boolean startUp;

    @ManyToOne
    @JoinColumn(name = "PropositionFinaleId", referencedColumnName = "ContratJuridiqueBM")
    private PropositionFinale propositionFinale;

    public ContratTripartiteFinal() {
    }

    public ContratTripartiteFinal(Integer contratJuridiqueBM, Boolean tasvee, Boolean fonds, Boolean startUp, PropositionFinale propositionFinale) {
        this.contratJuridiqueBM = contratJuridiqueBM;
        this.tasvee = tasvee;
        this.fonds = fonds;
        this.startUp = startUp;
        this.propositionFinale = propositionFinale;
    }

    // Getters et Setters

    public Integer getContratJuridiqueBM() {
        return contratJuridiqueBM;
    }

    public void setContratJuridiqueBM(Integer contratJuridiqueBM) {
        this.contratJuridiqueBM = contratJuridiqueBM;
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

    public PropositionFinale getPropositionFinale() {
        return propositionFinale;
    }

    public void setPropositionFinale(PropositionFinale propositionFinale) {
        this.propositionFinale = propositionFinale;
    }
}
