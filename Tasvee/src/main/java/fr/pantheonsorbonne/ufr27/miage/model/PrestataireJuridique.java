package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "PrestataireJuridique")
public class PrestataireJuridique {

    @Id
    @Column(name = "SiretPrestataireJuridique")
    private String siretPrestataireJuridique;

    @Column(name = "email")
    private String email;

    public PrestataireJuridique() {
    }

    public PrestataireJuridique(String siretPrestataireJuridique, String email) {
        this.siretPrestataireJuridique = siretPrestataireJuridique;
        this.email = email;
    }

    public String getSiretPrestataireJuridique() {
        return siretPrestataireJuridique;
    }

    public void setSiretPrestataireJuridique(String siretPrestataireJuridique) {
        this.siretPrestataireJuridique = siretPrestataireJuridique;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
