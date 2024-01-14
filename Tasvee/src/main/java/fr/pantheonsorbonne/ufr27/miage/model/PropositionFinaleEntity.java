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


    @JoinColumn(name = "SiretFonds", referencedColumnName = "SiretFonds")
    private Integer siretFond;

    public PropositionFinaleEntity() {
    }

    public PropositionFinaleEntity( Integer leveedeFondsFinale, Integer pourcentagePartFinale, Integer siretFond) {
        this.leveedeFondsFinale = leveedeFondsFinale;
        this.pourcentagePartFinale = pourcentagePartFinale;
        this.siretFond = siretFond;
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

    public Integer getSiretFond() {
        return siretFond;
    }

    public void setSiretFond(Integer siretFond) {
        this.siretFond = siretFond;
    }
}
