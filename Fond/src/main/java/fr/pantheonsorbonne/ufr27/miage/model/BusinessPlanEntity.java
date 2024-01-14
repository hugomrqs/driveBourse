package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class BusinessPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBusinessPlan", nullable = false)
    private Integer IDBusinessPlan;

    @JoinColumn(name = "SiretStartUp", nullable = false)
    private Integer SiretStartUp;

    @JoinColumn(name = "IDOnePager", nullable = false)
    private Integer IDOnePager;

    public Integer getIDBusinessPlan() {
        return IDBusinessPlan;
    }

    public void setIDBusinessPlan(Integer IDBusinessPlan) {
        this.IDBusinessPlan = IDBusinessPlan;
    }

    public Integer getSiretStartUp() {
        return SiretStartUp;
    }

    public void setSiretStartUp(Integer SiretStartUp) {
        this.SiretStartUp = SiretStartUp;
    }

    public Integer getIDOnePager() {
        return IDOnePager;
    }

    public void setIDOnePager(Integer IDOnePager) {
        this.IDOnePager = IDOnePager;
    }
}
