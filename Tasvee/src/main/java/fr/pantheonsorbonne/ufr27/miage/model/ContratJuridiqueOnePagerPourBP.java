package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ContratJuridiqueOnePagerPourBP")
public class ContratJuridiqueOnePagerPourBP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM")
    private Integer contratJuridiqueBM;

    @Column(name = "tasvee")
    private Boolean tasvee;

    @Column(name = "fonds")
    private Boolean fonds;

    @Column(name = "SiretTasvee")
    private int siretTasvee;

    @ManyToOne
    @JoinColumn(name = "SiretFonds", referencedColumnName = "SiretFonds")
    private Fond siretFonds;

    @ManyToOne
    @JoinColumn(name = "IDOnePager", referencedColumnName = "IDOnePager")
    private OnePager idOnPager;

    public ContratJuridiqueOnePagerPourBP() {
    }

    public ContratJuridiqueOnePagerPourBP(Boolean tasvee, Boolean fonds, int siretTasvee, Fond siretFonds, OnePager idOnPager) {
        this.tasvee = tasvee;
        this.fonds = fonds;
        this.siretTasvee = siretTasvee;
        this.siretFonds = siretFonds;
        this.idOnPager = idOnPager;
    }

    public Integer getContratJuridiqueBM() {
        return contratJuridiqueBM;
    }

    public Boolean getTasvee() {
        return tasvee;
    }

    public Boolean getFonds() {
        return fonds;
    }

    public int getSiretTasvee() {
        return siretTasvee;
    }

    public Fond getSiretFonds() {
        return siretFonds;
    }

    public OnePager getIdOnPager() {
        return idOnPager;
    }

    // Setters
    public void setContratJuridiqueBM(Integer contratJuridiqueBM) {
        this.contratJuridiqueBM = contratJuridiqueBM;
    }

    public void setTasvee(Boolean tasvee) {
        this.tasvee = tasvee;
    }

    public void setFonds(Boolean fonds) {
        this.fonds = fonds;
    }

    public void setSiretTasvee(int siretTasvee) {
        this.siretTasvee = siretTasvee;
    }

    public void setSiretFonds(Fond siretFonds) {
        this.siretFonds = siretFonds;
    }

    public void setIdOnPager(OnePager idOnPager) {
        this.idOnPager = idOnPager;
    }
}
