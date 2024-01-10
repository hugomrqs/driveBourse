package fr.pantheonsorbonne.ufr27.miage.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PrestataireFinancier")
public class PrestataireFinancier {

    @Id
    @Column(name = "SiretPrestataireFinancier")
    private String siretPrestataireFinancier;

    @Column(name = "mail")
    private String mail;

    public PrestataireFinancier() {
    }

    public PrestataireFinancier(String siretPrestataireFinancier, String mail) {
        this.siretPrestataireFinancier = siretPrestataireFinancier;
        this.mail = mail;
    }

    // Getters et Setters

    public String getSiretPrestataireFinancier() {
        return siretPrestataireFinancier;
    }

    public void setSiretPrestataireFinancier(String siretPrestataireFinancier) {
        this.siretPrestataireFinancier = siretPrestataireFinancier;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
