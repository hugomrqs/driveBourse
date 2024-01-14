package fr.pantheonsorbonne.ufr27.miage.dto;


public class NDADTOCommercialisationDTO extends NDADTO<PropositionDTO> {
    private boolean signatureFonds;
    private boolean signatureEntreprise;
    private Integer idPropositionDTO;

    public NDADTOCommercialisationDTO(PropositionDTO propositionDTO, boolean signatureTasvee, boolean signatureFonds, boolean signatureEntreprise) {
        super(propositionDTO, signatureTasvee);
        this.idPropositionDTO = propositionDTO.idProposition();
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

    public Integer getIdPropositionDTO() {
        return idPropositionDTO;
    }

    public void setIdPropositionDTO(Integer idPropositionDTO) {
        this.idPropositionDTO = idPropositionDTO;
    }
}

