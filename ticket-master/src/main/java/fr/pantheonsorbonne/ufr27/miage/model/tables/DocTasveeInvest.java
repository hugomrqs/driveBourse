package fr.pantheonsorbonne.ufr27.miage.model.tables;
import jakarta.persistence.*;
import java.io.File;

@Entity
@Table(name = "DocTasveeInvest")
public class DocTasveeInvest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFonds", nullable = false)
    protected int idFonds;

    @Id
    @JoinColumn(name = "idTasvee", nullable = false)
    protected int idTasvee;

    @Column(name = "onePager", nullable = false)
    protected File onePager;

    @Column(name = "NDA1", nullable = false)
    protected File NDA1;

    @Column(name = "synthDesSynth", nullable = false)
    protected File synthDesSynth;

    @Column(name = "NDA2", nullable = false)
    protected File NDA2;

    @Column(name = "propDeFond", nullable = false)
    protected File propDeFond;

    public int getIdFonds() { return idFonds; }

    public void setIdFonds(int idFonds) { this.idFonds = idFonds; }

    public int getIdTasvee() {return idTasvee;}

    public void setIdTasvee(int idTasvee) {this.idTasvee = idTasvee;}

    public File getOnePager() { return onePager; }

    public void setOnePager(File OnePager) { this.onePager = OnePager; }

    public File getNDA1() { return NDA1; }

    public void setNDA1(File NDA1) { this.NDA1 = NDA1; }

    public File getSynthDesSynth() { return synthDesSynth; }

    public void setSynthDesSynth(File synthDesSynth) { this.synthDesSynth = synthDesSynth; }

    public File getNDA2() { return NDA2; }

    public void setNDA2(File NDA2) { this.NDA2 = NDA2; }

    public File getPropDeFond() { return propDeFond; }

    public void setPropDeFond(File propDeFond) { this.propDeFond = propDeFond; }


}
