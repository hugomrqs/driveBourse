package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "BusinessModel")
public class BusinessModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessModel")
    private Integer idBusinessModel;

    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private String siretStartUp;

    @Column(name = "argentLeveeXpTasvee")
    private Integer argentLeveeXpTasvee;

    @Column(name = "partCedeeXpTasvee")
    private Integer partCedeeXpTasvee;

    public BusinessModel() {
    }

    public BusinessModel(Integer argentLeveeXpTasvee,
                         Integer partCedeeXpTasvee,
                         String siretStartUp) {
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
    public String getsiretStartUp() {
        return siretStartUp;
    }

    public void setsiretStartUp(String siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public Integer
    getArgentLeveeXpTasvee() {
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
