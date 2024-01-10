package fr.pantheonsorbonne.ufr27.miage.java.dto;

import fr.pantheonsorbonne.ufr27.miage.dto.common.NDA;

public class NDADeal extends NDA<BusinessModelDTO> {
    private boolean signatureEntreprise;

    public NDADeal(BusinessModelDTO businessModelDTO, boolean signatureTasvee, boolean signatureEntreprise) {
        super(businessModelDTO, signatureTasvee);
        this.signatureEntreprise = signatureEntreprise;
    }
    public boolean isSignatureEntreprise() {
        return signatureEntreprise;
    }

    public void setSignatureEntreprise(boolean signatureEntreprise) {
        this.signatureEntreprise = signatureEntreprise;
    }
}
