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

    @OneToOne
    @JoinColumn(name = "IDOnePager", nullable = false)
    private OnePagerEntity IDOnePager;

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

    public OnePagerEntity getIDOnePager() {
        return this.IDOnePager;
    }

    public void setOnePager(OnePagerEntity OnePager) {
        this.IDOnePager = OnePager;
    }
}
