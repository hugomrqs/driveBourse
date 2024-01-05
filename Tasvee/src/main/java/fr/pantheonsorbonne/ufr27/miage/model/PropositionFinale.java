package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "PropositionFinale")
public class PropositionFinale {

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
    private Fond fond;

    public PropositionFinale() {
    }

    public PropositionFinale(Integer idPropositionFinale, Integer leveedeFondsFinale, Integer pourcentagePartFinale, Fond fond) {
        this.idPropositionFinale = idPropositionFinale;
        this.leveedeFondsFinale = leveedeFondsFinale;
        this.pourcentagePartFinale = pourcentagePartFinale;
        this.fond = fond;
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

    public Fond getFonds() {
        return fond;
    }

    public void setFonds(Fond fond) {
        this.fond = fond;
    }
}
