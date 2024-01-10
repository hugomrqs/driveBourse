package fr.pantheonsorbonne.ufr27.miage.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    @Column(name = "IsInterested")
    private boolean isInterested;

    public Fond() {
    }

    public Fond(String siretFonds, String mail, String iban,boolean isInterested) {
        this.siretFonds = siretFonds;
        this.mail = mail;
        this.iban = iban;
        this.isInterested = isInterested;
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

    public boolean getIsInterested() {
        return isInterested;
    }

    public void setIsInterested(boolean isInterested) {
        this.isInterested = isInterested;
    }

}
