package fr.pantheonsorbonne.ufr27.miage.dto;

public class NDADeal extends NDA<BusinessModel> {
    private boolean signatureEntreprise;

    public NDADeal(BusinessModel businessModel, boolean signatureTasvee, boolean signatureEntreprise) {
        super(businessModel, signatureTasvee);
        this.signatureEntreprise = signatureEntreprise;
    }
    public boolean isSignatureEntreprise() {
        return signatureEntreprise;
    }

    public void setSignatureEntreprise(boolean signatureEntreprise) {
        this.signatureEntreprise = signatureEntreprise;
    }
}
