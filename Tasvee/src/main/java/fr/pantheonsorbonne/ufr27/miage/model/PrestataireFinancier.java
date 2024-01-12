package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "PrestataireFinancier")
public class PrestataireFinancier {

    @Id
    @Column(name = "SiretPrestataireFinancier")
    private Integer siretPrestataireFinancier;

    @Column(name = "mail")
    private String mail;

    public PrestataireFinancier() {
    }

    public PrestataireFinancier(Integer siretPrestataireFinancier, String mail) {
        this.siretPrestataireFinancier = siretPrestataireFinancier;
        this.mail = mail;
    }

    // Getters et Setters

    public Integer getSiretPrestataireFinancier() {
        return siretPrestataireFinancier;
    }

    public void setSiretPrestataireFinancier(Integer siretPrestataireFinancier) {
        this.siretPrestataireFinancier = siretPrestataireFinancier;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
