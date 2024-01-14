package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ContratTripartiteFinal")
public class ContratTripartiteFinalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDContratTripartiteFinal")
    private Integer contratJuridiqueBM;

    @Column(name = "tasvee")
    private Boolean tasvee;

    @Column(name = "fonds")
    private Boolean fonds;
    @Column(name = "startUp")
    private Boolean startUp;

    @ManyToOne
    @JoinColumn(name = "PropositionFinaleId", referencedColumnName = "IdPropositionFinale")
    private PropositionFinaleEntity propositionFinaleEntity;

    public ContratTripartiteFinalEntity() {
    }

    public ContratTripartiteFinalEntity(Boolean tasvee, Boolean fonds, Boolean startUp, PropositionFinaleEntity propositionFinaleEntity) {
        this.tasvee = tasvee;
        this.fonds = fonds;
        this.startUp = startUp;
        this.propositionFinaleEntity = propositionFinaleEntity;
    }

//Ajouter les Sirets pou pouvoir faire des jointure dessus
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

    public PropositionFinaleEntity getPropositionFinale() {
        return propositionFinaleEntity;
    }

    public void setPropositionFinale(PropositionFinaleEntity propositionFinaleEntity) {
        this.propositionFinaleEntity = propositionFinaleEntity;
    }
}
