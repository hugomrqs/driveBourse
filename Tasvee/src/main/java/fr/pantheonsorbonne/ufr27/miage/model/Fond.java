package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Fonds")
public class Fond {

    @Id
    @Column(name = "SiretFonds")
    private String siretFonds;

    @Column(name = "mail")
    private String mail;

    @Column(name = "IBAN")
    private String iban;

    public Fond() {
    }

    public Fond(String siretFonds, String mail, String iban) {
        this.siretFonds = siretFonds;
        this.mail = mail;
        this.iban = iban;
    }

    // Getters et Setters

    public String getSiretFonds() {
        return siretFonds;
    }

    public void setSiretFonds(String siretFonds) {
        this.siretFonds = siretFonds;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
