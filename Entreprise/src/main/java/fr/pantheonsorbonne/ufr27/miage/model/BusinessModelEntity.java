package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "BusinessModel")
public class BusinessModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessModel")
    private Integer idBusinessModel;

    @Column(name = "argentLeveeXpTasvee")
    private Integer argentLeveeXpTasvee;

    @Column(name = "partCedeeXpTasvee")
    private Integer partCedeeXpTasvee;

    public BusinessModelEntity() {
    }

    public BusinessModelEntity(Integer argentLeveeXpTasvee,
                               Integer partCedeeXpTasvee,
                               StartUpEntity siretStartUp) {
        this.argentLeveeXpTasvee = argentLeveeXpTasvee;
        this.partCedeeXpTasvee = partCedeeXpTasvee;
    }

    public BusinessModelEntity(int i, int i1) {
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