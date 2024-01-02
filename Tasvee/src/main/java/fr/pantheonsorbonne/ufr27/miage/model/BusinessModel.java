package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "BusinessModel")
public class BusinessModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessModel")
    private Integer idBusinessModel;

    @Column(name = "argentLeveeXpTasvee")
    private Integer argentLeveeXpTasvee;

    @Column(name = "partCedeeXpTasvee")
    private Integer partCedeeXpTasvee;

    public BusinessModel() {
    }

    public BusinessModel(Integer argentLeveeXpTasvee,
                         Integer partCedeeXpTasvee) {
        this.argentLeveeXpTasvee = argentLeveeXpTasvee;
        this.partCedeeXpTasvee = partCedeeXpTasvee;
    }

    public Integer getIdBusinessModel() {
        return idBusinessModel;
    }

    public void setIdBusinessModel(Integer idBusinessModel) {
        this.idBusinessModel = idBusinessModel;
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
