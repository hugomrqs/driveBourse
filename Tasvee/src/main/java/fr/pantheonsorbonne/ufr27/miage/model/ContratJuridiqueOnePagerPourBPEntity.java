package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ContratJuridiqueOnePagerPourBP")
public class ContratJuridiqueOnePagerPourBPEntity {

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
    private FondEntity siretFonds;

    @ManyToOne
    @JoinColumn(name = "IDOnePager", referencedColumnName = "IDOnePager")
    private OnePagerEntity idOnPager;

    public ContratJuridiqueOnePagerPourBPEntity() {
    }

    public ContratJuridiqueOnePagerPourBPEntity(Boolean tasvee, Boolean fonds, int siretTasvee, FondEntity siretFonds, OnePagerEntity idOnPager) {
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

    public FondEntity getSiretFonds() {
        return siretFonds;
    }

    public OnePagerEntity getIdOnPager() {
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

    public void setSiretFonds(FondEntity siretFonds) {
        this.siretFonds = siretFonds;
    }

    public void setIdOnPager(OnePagerEntity idOnPager) {
        this.idOnPager = idOnPager;
    }
}
