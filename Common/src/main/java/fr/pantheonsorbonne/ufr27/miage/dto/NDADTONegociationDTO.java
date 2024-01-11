package fr.pantheonsorbonne.ufr27.miage.dto;

public class NDADTONegociationDTO extends NDADTO<PropositionDTO> {
    private boolean signatureFonds;

    public NDADTONegociationDTO(PropositionDTO propositionDTO, boolean signatureTasvee, boolean signatureFonds) {
        super(propositionDTO, signatureTasvee);
        this.signatureFonds = signatureFonds;
    }

    // Getters et setters
    public boolean isSignatureFonds() {
        return signatureFonds;
    }

    public void setSignatureFonds(boolean signatureFonds) {
        this.signatureFonds = signatureFonds;
    }
}
