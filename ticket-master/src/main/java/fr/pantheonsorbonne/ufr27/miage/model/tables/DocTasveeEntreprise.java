package fr.pantheonsorbonne.ufr27.miage.model.tables;
import jakarta.persistence.*;
import java.io.File;

@Entity
@Table(name = "DocTasveeEntreprise")
public class DocTasveeEntreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEntreprise", nullable = false)
    protected int idEntreprise;

    @Id
    @JoinColumn(name = "idTasvee", nullable = false)
    protected int idTasvee;

    @Column(name = "bilanComptable", nullable = false)
    protected File bilanComptable;

    @Column(name = "statut", nullable = false)
    protected File statut;

    @Column(name = "docSiret", nullable = false)
    protected File docSiret;

    @Column(name = "CVDirigeant", nullable = false)
    protected File CVDirigeant;

    @Column(name = "urlSite", nullable = false)
    protected String urlSite;

    @Column(name = "urlLinkedin", nullable = false)
    protected String urlLinkedin;

    @Column(name = "NDA0", nullable = false)
    protected File NDA0;

    public int getIdEntreprise() { return this.idEntreprise; }

    public void setIdEntreprise(int idEntreprise) { this.idEntreprise = idEntreprise; }

    public int getIdTasvee() { return this.idTasvee; }

    public void setIdTasvee(int idTasvee) { this.idTasvee = idTasvee; }

    public File getBilanComptable() { return this.bilanComptable; }

    public void setBilanComptable(File bilanComptable) { this.bilanComptable = bilanComptable; }

    public File getStatut() { return this.statut; }

    public void setStatut(File statut) { this.statut = statut; }

    public File getDocSiret() { return this.docSiret; }

    public void setDocSiret(File docSiret) { this.docSiret = docSiret; }

    public File getCVDirigeant() { return this.CVDirigeant; }

    public void setCVDirigeant(File CVDirigeant) { this.CVDirigeant = CVDirigeant; }

    public String getUrlSite() { return this.urlSite; }

    public void setUrlSite(String urlSite) { this.urlSite = urlSite; }

    public String getUrlLinkedin() { return this.urlLinkedin; }

    public void setUrlLinkedin(String urlLinkedin) { this.urlLinkedin = urlLinkedin; }

    public File getNDA0() { return this.NDA0; }

    public void setNDA0(File NDA0) { this.NDA0 = NDA0; }

}
