package fr.pantheonsorbonne.ufr27.miage.dto;


import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;


public class NDADTOCommercialisationDTO extends NDADTO<PropositionDTO> {
    private boolean signatureFonds;
    private boolean signatureEntreprise;

    public NDADTOCommercialisationDTO(int numeroContrat, PropositionDTO propositionDTO,
                                      int siretTasvee,
                                      boolean signatureTasvee,
                                      boolean signatureFonds,
                                      boolean signatureEntreprise) {

    super(numeroContrat, propositionDTO, siretTasvee,signatureTasvee);
    this.signatureFonds = signatureFonds;
    this.signatureEntreprise = signatureEntreprise;
    }

    public boolean isSignatureFonds() {
        return signatureFonds;
    }

    public void setSignatureFonds(boolean signatureFonds) {
        this.signatureFonds = signatureFonds;
    }

    public boolean isSignatureEntreprise() {
        return signatureEntreprise;
    }

    public void setSignatureEntreprise(boolean signatureEntreprise) {
        this.signatureEntreprise = signatureEntreprise;
    }
}

