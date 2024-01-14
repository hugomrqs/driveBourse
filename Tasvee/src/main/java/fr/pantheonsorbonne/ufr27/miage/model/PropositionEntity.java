package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "PropositionFinale")
public class PropositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProposition", nullable = false)
    private Integer IDProposition;

    @JoinColumn(name = "siretFond", nullable = false)
    private Integer siretFond;

    @JoinColumn(name = "siretStartUp", nullable = false)
    private Integer siretStartUp;

    @Column (name = "leveeDeFonds", nullable = false)
    private Integer leveeDeFonds;

    @Column(name = "pourcentagePart", nullable = false)
    private Integer pourcentagePart;

    @Column(name = "etatProposition", nullable = false)
    private boolean etatProposition;

    // Constructeur
    public PropositionEntity(Integer siretFond, Integer siretStartUp, Integer leveeDeFonds, Integer pourcentagePart, boolean etatProposition) {
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

    public Integer getSiretFond() {
        return siretFond;
    }

    public void setSiretFonds(Integer SiretFond) {
        this.siretFond = SiretFond;
    }

    public Integer getSiretStartUp() {return siretStartUp;}

    public void setSiretStartUp(Integer siretStartUp) {this.siretStartUp = siretStartUp;}

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
