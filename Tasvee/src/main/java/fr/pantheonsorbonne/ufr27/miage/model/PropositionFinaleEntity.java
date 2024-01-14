package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "PropositionFinale")
public class PropositionFinaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPropositionFinale")
    private Integer idPropositionFinale;

    @Column(name = "leveedeFondsFinale")
    private Integer leveedeFondsFinale;

    @Column(name = "pourcentagePartFinale")
    private Integer pourcentagePartFinale;

    @ManyToOne
    @JoinColumn(name = "SiretFonds", referencedColumnName = "SiretFonds")
    private FondEntity fondEntity;

    public PropositionFinaleEntity() {
    }

    public PropositionFinaleEntity(Integer idPropositionFinale, Integer leveedeFondsFinale, Integer pourcentagePartFinale, FondEntity fondEntity) {
        this.idPropositionFinale = idPropositionFinale;
        this.leveedeFondsFinale = leveedeFondsFinale;
        this.pourcentagePartFinale = pourcentagePartFinale;
        this.fondEntity = fondEntity;
    }

    public Integer getContratJuridiqueBM() {
        return idPropositionFinale;
    }

    public void setContratJuridiqueBM(Integer idPropositionFinale) {
        this.idPropositionFinale = idPropositionFinale;
    }

    public Integer getLeveedeFondsFinale() {
        return leveedeFondsFinale;
    }

    public void setLeveedeFondsFinale(Integer leveedeFondsFinale) {
        this.leveedeFondsFinale = leveedeFondsFinale;
    }

    public Integer getPourcentagePartFinale() {
        return pourcentagePartFinale;
    }

    public void setPourcentagePartFinale(Integer pourcentagePartFinale) {
        this.pourcentagePartFinale = pourcentagePartFinale;
    }

    public FondEntity getFonds() {
        return fondEntity;
    }

    public void setFonds(FondEntity fondEntity) {
        this.fondEntity = fondEntity;
    }
}
