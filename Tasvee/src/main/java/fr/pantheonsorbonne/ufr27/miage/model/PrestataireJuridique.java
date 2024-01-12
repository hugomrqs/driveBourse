package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;

@Entity
@Table(name = "PrestataireJuridique")
public class PrestataireJuridique {

    @Id
    @Column(name = "SiretPrestataireJuridique")
    private Integer siretPrestataireJuridique;

    @Column(name = "email")
    private String email;

    public PrestataireJuridique() {
    }

    public PrestataireJuridique(Integer siretPrestataireJuridique, String email) {
        this.siretPrestataireJuridique = siretPrestataireJuridique;
        this.email = email;
    }

    public Integer getSiretPrestataireJuridique() {
        return siretPrestataireJuridique;
    }

    public void setSiretPrestataireJuridique(Integer siretPrestataireJuridique) {
        this.siretPrestataireJuridique = siretPrestataireJuridique;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
