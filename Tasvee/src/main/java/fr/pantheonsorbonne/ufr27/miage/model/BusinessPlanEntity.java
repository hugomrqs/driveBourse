package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "BusinessPlan")
public class BusinessPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessPlan")
    private Integer idBusinessPlan;

    @ManyToOne
    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private StartUpEntity siretStartUp;

    @ManyToOne
    @JoinColumn(name = "IDOnePager", referencedColumnName = "IDOnePager")
    private OnePagerEntity idOnePagerEntity;

    public BusinessPlanEntity() {
    }
    public BusinessPlanEntity(StartUpEntity siretStartUp, OnePagerEntity idOnePagerEntity) {
        this.siretStartUp = siretStartUp;
        this.idOnePagerEntity = idOnePagerEntity;
    }

    public Integer getIdBusinessPlan() {
        return idBusinessPlan;
    }

    public StartUpEntity getSiretStartUp() {
        return siretStartUp;
    }

    public OnePagerEntity getIdOnePager() {
        return idOnePagerEntity;
    }

    // Setters
    public void setIdBusinessPlan(Integer idBusinessPlan) {
        this.idBusinessPlan = idBusinessPlan;
    }

    public void setSiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public void setIdOnePager(OnePagerEntity idOnePagerEntity) {
        this.idOnePagerEntity = idOnePagerEntity;
    }
}
