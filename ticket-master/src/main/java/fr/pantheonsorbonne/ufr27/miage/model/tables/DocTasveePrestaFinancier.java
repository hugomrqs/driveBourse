package fr.pantheonsorbonne.ufr27.miage.model.tables;
import jakarta.persistence.*;
import java.io.File;

@Entity
@Table(name = "DocTasveePrestaFinancier")
public class DocTasveePrestaFinancier{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPrestaFin", nullable = false)
    protected int idPrestaFin;

    @Id
    @JoinColumn(name = "idTasvee", nullable = false)
    protected int idTasvee;

    @Column(name = "analyFin", nullable = false)
    protected File analyFin;

    public int getIdPrestaFin() { return this.idPrestaFin; }

    public void setIdPrestaFin(int idPrestaFin) { this.idPrestaFin = idPrestaFin; }

    public int getIdTasvee() { return this.idTasvee; }

    public void setIdTasvee(int idTasvee) { this.idTasvee = idTasvee; }

    public File getanalyFin() { return this.analyFin; }

    public void setanalyFin(File analyFin) { this.analyFin = analyFin; }

}
