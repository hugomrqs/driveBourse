package fr.pantheonsorbonne.ufr27.miage.model.tables;
import jakarta.persistence.*;
import java.io.File;

@Entity
@Table(name = "TasveeOperation")
public class Tasvee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTasvee", nullable = false)
    protected int idTasvee;

    @Column(name = "nom", nullable = false)
    protected String nom;

    @Column(name = "synthCadrage", nullable = false)
    protected File synthCadrage;

    @Column(name = "synthFinanciere", nullable = false)
    protected File synthFinanciere;

    @Column(name = "synthJuridique", nullable = false)
    protected File synthJuridique;

    @Column(name = "NDA3", nullable = false)
    protected File NDA3;

    public int getIdTasvee() {return this.idTasvee;}

    public void setIdTasvee(int idTasvee) {this.idTasvee = idTasvee;}

    public String getNom() {return this.nom;}

    public void setNom(String nom) {this.nom = nom;}

    public File getSynthCadrage() {return this.synthCadrage;}

    public void setSynthCadrage(File synthCadrage) {this.synthCadrage = synthCadrage;}

    public File getSynthFinanciere() {return this.synthFinanciere;}

    public void setSynthFinanciere(File synthFinanciere) {this.synthFinanciere = synthFinanciere;}

    public File getSynthJuridique() {return this.synthJuridique;}

    public void setSynthJuridique(File synthJuridique) {this.synthJuridique = synthJuridique;}

    public File getNDA3() {return this.NDA3;}

    public void setNDA3(File NDA3) {this.NDA3 = NDA3;}
}
