package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;


@Entity
public class Proposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProposition", nullable = false)
    private Integer IDProposition;

    @JoinColumn(name = "SiretFonds", nullable = false)
    private Integer SiretFonds;

    @Column (name = "leveeDeFonds", nullable = false)
    private Integer leveeDeFonds;

    @Column(name = "pourcentagePart", nullable = false)
    private Integer pourcentagePart;

    @Column(name = "etatProposition", nullable = false)
    private boolean etatProposition;

    public Integer getIDProposition() {
        return IDProposition;
    }

    public void setIDProposition(Integer IDProposition) {
        this.IDProposition = IDProposition;
    }

    public Integer getSiretFonds() {
        return SiretFonds;
    }

    public void setSiretFonds(Integer SiretFonds) {
        this.SiretFonds = SiretFonds;
    }

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
