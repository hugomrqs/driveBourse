package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "BusinessPlan")
public class BusinessPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessPlan")
    private Integer idBusinessPlan;

    @ManyToOne
    @JoinColumn(name = "SiretStartUP", referencedColumnName = "SiretStartUP")
    private StartUpEntity siretStartUp;

    @ManyToOne
    @JoinColumn(name = "IDOnePager", referencedColumnName = "IDOnePager")
    private OnePager idOnePager;

    public BusinessPlan() {
    }
    public BusinessPlan(StartUpEntity siretStartUp, OnePager idOnePager) {
        this.siretStartUp = siretStartUp;
        this.idOnePager = idOnePager;
    }

    public Integer getIdBusinessPlan() {
        return idBusinessPlan;
    }

    public StartUpEntity getSiretStartUp() {
        return siretStartUp;
    }

    public OnePager getIdOnePager() {
        return idOnePager;
    }

    // Setters
    public void setIdBusinessPlan(Integer idBusinessPlan) {
        this.idBusinessPlan = idBusinessPlan;
    }

    public void setSiretStartUp(StartUpEntity siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public void setIdOnePager(OnePager idOnePager) {
        this.idOnePager = idOnePager;
    }
}
