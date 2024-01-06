package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "BusinessModel")
public class BusinessModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessModel")
    private Integer idBusinessModel;

    @ManyToOne
    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private StartUpEntity siretStartUp;

    @Column(name = "argentLeveeXpTasvee")
    private Integer argentLeveeXpTasvee;

    @Column(name = "partCedeeXpTasvee")
    private Integer partCedeeXpTasvee;

    public BusinessModel() {
    }

    public BusinessModel(Integer argentLeveeXpTasvee,
                         Integer partCedeeXpTasvee,
                         StartUpEntity siretStartUp) {
        this.argentLeveeXpTasvee = argentLeveeXpTasvee;
        this.partCedeeXpTasvee = partCedeeXpTasvee;
        this.siretStartUp = siretStartUp;
    }

    public Integer getIdBusinessModel() {
        return idBusinessModel;
    }

    public void setIdBusinessModel(Integer idBusinessModel) {
        this.idBusinessModel = idBusinessModel;
    }
    public StartUpEntity getsiretStartUp() {
        return siretStartUp;
    }

    public void setsiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public Integer getArgentLeveeXpTasvee() {
        return argentLeveeXpTasvee;
    }

    public void setArgentLeveeXpTasvee(Integer argentLeveeXpTasvee) {
        this.argentLeveeXpTasvee = argentLeveeXpTasvee;
    }

    public Integer getPartCedeeXpTasvee() {
        return partCedeeXpTasvee;
    }

    public void setPartCedeeXpTasvee(Integer partCedeeXpTasvee) {
        this.partCedeeXpTasvee = partCedeeXpTasvee;
    }
}
