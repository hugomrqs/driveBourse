package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "CVDirigeant")
public class CVDirigeantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCVDirigeant")
    private Integer idCVDirigeant;

    @Column(name = "ecole")
    private String ecole;

    @Column(name = "mainExperience")
    private String mainExperience;

    @Column(name = "lienLinkedin")
    private String lienLinkedin;

    @Column(name = "engagement")
    private Boolean engagement;

    public CVDirigeantEntity() {
    }

    public CVDirigeantEntity(String ecole, String mainExperience, String lienLinkedin, Boolean engagement) {
        this.ecole = ecole;
        this.mainExperience = mainExperience;
        this.lienLinkedin = lienLinkedin;
        this.engagement = engagement;
    }

    public Integer getIdCVDirigeant() {
        return idCVDirigeant;
    }

    public void setIdCVDirigeant(Integer idCVDirigeant) {
        this.idCVDirigeant = idCVDirigeant;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getMainExperience() {
        return mainExperience;
    }

    public void setMainExperience(String mainExperience) {
        this.mainExperience = mainExperience;
    }

    public String getLienLinkedin() {
        return lienLinkedin;
    }

    public void setLienLinkedin(String lienLinkedin) {
        this.lienLinkedin = lienLinkedin;
    }

    public Boolean getEngagement() {
        return engagement;
    }

    public void setEngagement(Boolean engagement) {
        this.engagement = engagement;
    }
}
