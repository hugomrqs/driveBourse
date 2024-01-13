package fr.pantheonsorbonne.ufr27.miage.dto;

public class NDADTODealDTO extends NDADTO<OnePagerDTO> {
    private boolean signatureEntreprise;

    public NDADTODealDTO(OnePagerDTO onePagerDTO, boolean signatureTasvee, boolean signatureEntreprise) {
        super(onePagerDTO, signatureTasvee);
        this.signatureEntreprise = signatureEntreprise;
    }
    public boolean isSignatureEntreprise() {
        return signatureEntreprise;
    }

    public void setSignatureEntreprise(boolean signatureEntreprise) {
        this.signatureEntreprise = signatureEntreprise;
    }
}
