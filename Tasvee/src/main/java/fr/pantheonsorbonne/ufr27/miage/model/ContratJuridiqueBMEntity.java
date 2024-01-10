package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ContratJuridiqueBM")
public class ContratJuridiqueBMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContratJuridiqueBM")
    private Integer contratJuridiqueBM;

    @Column(name = "TASVEE")
    private Boolean tasvee;

    @Column(name = "StartUP")
    private Boolean startUp;

    @Column(name = "PourcentageComissionTasvee")
    private Integer pourcentageComissionTasvee;

    @Column(name = "SiretTasvee")
    private Integer siretTasvee;

    @ManyToOne
    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private StartUpEntity siretStartUp;

    @ManyToOne
    @JoinColumn(name = "IDBusinessModel", referencedColumnName = "IDBusinessModel")
    private BusinessModelEntity idBusinessModel;

    public ContratJuridiqueBMEntity() {
    }

    public ContratJuridiqueBMEntity(Boolean tasvee,
                                    Boolean startUp,
                                    Integer pourcentageComissionTasvee,
                                    Integer siretTasvee,
                                    StartUpEntity siretStartUp,
                                    BusinessModelEntity idBusinessModel) {
        this.tasvee = tasvee;
        this.startUp = startUp;
        this.pourcentageComissionTasvee = pourcentageComissionTasvee;
        this.siretTasvee = siretTasvee;
        this.siretStartUp = siretStartUp;
        this.idBusinessModel = idBusinessModel;
    }

    public Integer getContratJuridiqueBM() {
        return contratJuridiqueBM;
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

    public Integer getSiretTasvee() {
        return siretTasvee;
    }

    public StartUpEntity getSiretStartUp() {
        return siretStartUp;
    }

    public BusinessModelEntity getIdBusinessModel() {
        return idBusinessModel;
    }

    public void setContratJuridiqueBM(Integer contratJuridiqueBM) {
        this.contratJuridiqueBM = contratJuridiqueBM;
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

    public void setSiretTasvee(Integer siretTasvee) {
        this.siretTasvee = siretTasvee;
    }

    public void setSiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public void setIdBusinessModel(BusinessModelEntity idBusinessModel) {
        this.idBusinessModel = idBusinessModel;
    }
}
