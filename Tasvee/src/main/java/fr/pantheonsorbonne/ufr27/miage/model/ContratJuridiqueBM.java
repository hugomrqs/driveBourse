package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ContratJuridiqueBM")
public class ContratJuridiqueBM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBMID")
    private Integer contratJuridiqueBMID;

    @Column(name = "TASVEE")
    private Boolean tasvee;

    @Column(name = "StartUP")
    private Boolean startUp;

    @Column(name = "PourcentageComissionTasvee")
    private Integer pourcentageComissionTasvee;

    @Column(name = "SiretTasvee")
    private String siretTasvee;

    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private String siretStartUp;

    @ManyToOne
    @JoinColumn(name = "IDBusinessModel", referencedColumnName = "IDBusinessModel")
    private BusinessModel idBusinessModel;

    public ContratJuridiqueBM() {
    }

    public ContratJuridiqueBM(Boolean tasvee,
                              Boolean startUp,
                              Integer pourcentageComissionTasvee,
                              String siretTasvee,
                              String siretStartUp,
                              BusinessModel idBusinessModel) {
        this.tasvee = tasvee;
        this.startUp = startUp;
        this.pourcentageComissionTasvee = pourcentageComissionTasvee;
        this.siretTasvee = siretTasvee;
        this.siretStartUp = siretStartUp;
        this.idBusinessModel = idBusinessModel;
    }

    public Integer getContratJuridiqueBMID() {
        return contratJuridiqueBMID;
    }

    public Boolean getTasvee() {
        return tasvee;
    }

    public Boolean getStartUp() {
        return startUp;
    }

    public Integer getPourcentageComissionTasvee() {
        return pourcentageComissionTasvee;
    }

    public String getSiretTasvee() {
        return siretTasvee;
    }

    public String getSiretStartUp() {
        return siretStartUp;
    }

    public BusinessModel getIdBusinessModel() {
        return idBusinessModel;
    }

    public void setContratJuridiqueBMID(Integer contratJuridiqueBM) {
        this.contratJuridiqueBMID = contratJuridiqueBM;
    }

    public void setTasvee(Boolean tasvee) {
        this.tasvee = tasvee;
    }

    public void setStartUp(Boolean startUp) {
        this.startUp = startUp;
    }

    public void setPourcentageComissionTasvee(Integer pourcentageComissionTasvee) {
        this.pourcentageComissionTasvee = pourcentageComissionTasvee;
    }

    public void setSiretTasvee(String siretTasvee) {
        this.siretTasvee = siretTasvee;
    }

    public void setSiretStartUp(String siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public void setIdBusinessModel(BusinessModel idBusinessModel) {
        this.idBusinessModel = idBusinessModel;
    }
}
