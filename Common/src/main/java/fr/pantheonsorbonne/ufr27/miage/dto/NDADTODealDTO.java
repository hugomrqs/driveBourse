package fr.pantheonsorbonne.ufr27.miage.dto;

public class NDADTODealDTO extends NDADTO<BusinessModelDTO> {
    private boolean signatureEntreprise;

    public NDADTODealDTO(int numeroContrat, BusinessModelDTO businessModelDTO, int siretTasvee,boolean signatureTasvee, boolean signatureEntreprise) {
        super(numeroContrat, businessModelDTO, siretTasvee, signatureTasvee);
        this.signatureEntreprise = signatureEntreprise;
    }
    public boolean isSignatureEntreprise() {
        return signatureEntreprise;
    }

    public void setSignatureEntreprise(boolean signatureEntreprise) {
        this.signatureEntreprise = signatureEntreprise;
    }
}
