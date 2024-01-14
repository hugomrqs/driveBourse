package fr.pantheonsorbonne.ufr27.miage.dto;

public class NDADTONegociationDTO extends NDADTO<PropositionDTO> {
    private boolean signatureFonds;

    public NDADTONegociationDTO(int numeroContrat, PropositionDTO propositionDTO, int siretTasvee, boolean signatureTasvee, boolean signatureFonds) {
        super(numeroContrat, propositionDTO, siretTasvee,signatureTasvee);
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
