package fr.pantheonsorbonne.ufr27.miage.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "StartUp")
public class StartUp {

    @Id
    @Column(name = "SiretStartUP")
    private String siretStartUp;

    @Column(name = "nombreDePersonne")
    private Integer nombreDePersonne;

    @Column(name = "lienSiteWeb")
    private String lienSiteWeb;

    @Column(name = "dateOfferForm")
    private LocalDateTime dateOfferForm;

    @Column(name = "mail")
    private String mail;

    @Column(name = "secteur")
    private String secteur;

    @Column(name = "numeroDeLevee")
    private Integer numeroDeLevee;

    @Column(name = "IBAN")
    private String iban;

    @Column(name = "argentLevee")
    private Integer argentLevee;

    @Column(name = "partCede")
    private Integer partCede;

    @ManyToOne
    @JoinColumn(name = "IDBilanComptable", referencedColumnName = "IDBilanComptable")
    private BilanComptable idBilanComptable;

    @ManyToOne
    @JoinColumn(name = "IDStatuts", referencedColumnName = "IDStatuts")
    private Statut idStatuts;

    @ManyToOne
    @JoinColumn(name = "IDCVDirigeant", referencedColumnName = "IDCVDirigeant")
    private CVDirigeant idCVDirigeant;

    @ManyToOne
    @JoinColumn(name = "IDBusinessModel", referencedColumnName = "IDBusinessModel")
    private BusinessModel idBusinessModel;

    @ManyToOne
    @JoinColumn(name = "IDOnePager", referencedColumnName = "IDOnePager")
    private OnePager idOnePager;

    public StartUp() {
    }

    public StartUp(String siretStartUp, Integer nombreDePersonne, String lienSiteWeb, LocalDateTime dateOfferForm, String mail, String secteur, Integer numeroDeLevee, String iban, Integer argentLevee, Integer partCede, BilanComptable idBilanComptable, Statut idStatuts, CVDirigeant idCVDirigeant, BusinessModel idBusinessModel, OnePager idOnePager) {
        this.siretStartUp = siretStartUp;
        this.nombreDePersonne = nombreDePersonne;
        this.lienSiteWeb = lienSiteWeb;
        this.dateOfferForm = dateOfferForm;
        this.mail = mail;
        this.secteur = secteur;
        this.numeroDeLevee = numeroDeLevee;
        this.iban = iban;
        this.argentLevee = argentLevee;
        this.partCede = partCede;
        this.idBilanComptable = idBilanComptable;
        this.idStatuts = idStatuts;
        this.idCVDirigeant = idCVDirigeant;
        this.idBusinessModel = idBusinessModel;
        this.idOnePager = idOnePager;
    }

    // Getters
    public String getSiretStartUp() {
        return siretStartUp;
    }

    public Integer getNombreDePersonne() {
        return nombreDePersonne;
    }

    public String getLienSiteWeb() {
        return lienSiteWeb;
    }

    public LocalDateTime getDateOfferForm() {
        return dateOfferForm;
    }

    public String getMail() {
        return mail;
    }

    public String getSecteur() {
        return secteur;
    }

    public Integer getNumeroDeLevee() {
        return numeroDeLevee;
    }

    public String getIban() {
        return iban;
    }

    public Integer getArgentLevee() {
        return argentLevee;
    }

    public Integer getPartCede() {
        return partCede;
    }

    public BilanComptable getIdBilanComptable() {
        return idBilanComptable;
    }

    public Statut getIdStatuts() {
        return idStatuts;
    }

    public CVDirigeant getIdCVDirigeant() {
        return idCVDirigeant;
    }

    public BusinessModel getIdBusinessModel() {
        return idBusinessModel;
    }

    public OnePager getIdOnePager() {
        return idOnePager;
    }

    // Setters
    public void setSiretStartUp(String siretStartUp) {
        this.siretStartUp = siretStartUp;
    }

    public void setNombreDePersonne(Integer nombreDePersonne) {
        this.nombreDePersonne = nombreDePersonne;
    }

    public void setLienSiteWeb(String lienSiteWeb) {
        this.lienSiteWeb = lienSiteWeb;
    }

    public void setDateOfferForm(LocalDateTime dateOfferForm) {
        this.dateOfferForm = dateOfferForm;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setNumeroDeLevee(Integer numeroDeLevee) {
        this.numeroDeLevee = numeroDeLevee;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setArgentLevee(Integer argentLevee) {
        this.argentLevee = argentLevee;
    }

    public void setPartCede(Integer partCede) {
        this.partCede = partCede;
    }

    public void setIdBilanComptable(BilanComptable idBilanComptable) {
        this.idBilanComptable = idBilanComptable;
    }

    public void setIdStatuts(Statut idStatuts) {
        this.idStatuts = idStatuts;
    }

    public void setIdCVDirigeant(CVDirigeant idCVDirigeant) {
        this.idCVDirigeant = idCVDirigeant;
    }

    public void setIdBusinessModel(BusinessModel idBusinessModel) {
        this.idBusinessModel = idBusinessModel;
    }

    public void setIdOnePager(OnePager idOnePager) {
        this.idOnePager = idOnePager;
    }
}
