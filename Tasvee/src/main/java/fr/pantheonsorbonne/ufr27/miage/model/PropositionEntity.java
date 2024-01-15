package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "PropositionFinale")
public class PropositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPropositionFinale", nullable = false)
    private Integer IDProposition;

    @ManyToOne
    @JoinColumn(name = "SiretFonds", nullable = false)
    private FondEntity siretFond;

    @ManyToOne
    @JoinColumn(name = "SiretStartUp", nullable = false)
    private StartUpEntity siretStartUp;

    @Column (name = "leveeDeFonds", nullable = false)
    private Integer leveeDeFonds;

    @Column(name = "pourcentagePart", nullable = false)
    private Integer pourcentagePart;

    @Column(name = "etatProposition", nullable = false)
    private boolean etatProposition;

    // Constructeur
    public PropositionEntity(FondEntity siretFond, StartUpEntity siretStartUp, Integer leveeDeFonds, Integer pourcentagePart, boolean etatProposition) {
        this.siretFond = siretFond;
        this.siretStartUp = siretStartUp;
        this.leveeDeFonds = leveeDeFonds;
        this.pourcentagePart = pourcentagePart;
        this.etatProposition = etatProposition;
    }

    public PropositionEntity() {

    }

    public Integer getIDProposition() {
        return IDProposition;
    }

    public void setIDProposition(Integer IDProposition) {
        this.IDProposition = IDProposition;
    }

    public FondEntity getSiretFond() {
        return siretFond;
    }

    public void setSiretFonds(FondEntity SiretFond) {
        this.siretFond = SiretFond;
    }

    public StartUpEntity getSiretStartUp() {return siretStartUp;}

    public void setSiretStartUp(StartUpEntity siretStartUp) {this.siretStartUp = siretStartUp;}

    public Integer getLeveeDeFonds() {
        return leveeDeFonds;
    }

    public void setLeveeDeFonds(Integer leveeDeFonds) {
        this.leveeDeFonds = leveeDeFonds;
    }

    public Integer getPourcentagePart() {
        return pourcentagePart;
    }

    public void setPourcentagePart(Integer pourcentagePart) {
        this.pourcentagePart = pourcentagePart;
    }

    public boolean getEtatProposition() {
        return etatProposition;
    }

    public void setEtatProposition(boolean etatP) {
        this.etatProposition = etatP;
    }

}
