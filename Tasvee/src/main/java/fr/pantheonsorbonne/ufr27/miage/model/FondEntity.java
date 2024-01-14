package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Fonds")
public class FondEntity {

    @Id
    @Column(name = "SiretFonds")
    private Integer siretFonds;

    @Column(name = "mail")
    private String mail;

    @Column(name = "IBAN")
    private String iban;
    @Column(name = "IsInterested")
    private boolean isInterested;

    public FondEntity() {
    }

    public FondEntity(Integer siretFonds, String mail, String iban, boolean isInterested) {
        this.siretFonds = siretFonds;
        this.mail = mail;
        this.iban = iban;
        this.isInterested = isInterested;
    }

    // Getters et Setters

    public Integer getSiretFonds() {
        return siretFonds;
    }

    public void setSiretFonds(Integer siretFonds) {
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
