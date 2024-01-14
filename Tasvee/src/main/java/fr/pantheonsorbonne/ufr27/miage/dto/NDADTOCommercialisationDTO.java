package fr.pantheonsorbonne.ufr27.miage.dto;


import fr.pantheonsorbonne.ufr27.miage.helper.Helper;

public class NDADTOCommercialisationDTO extends NDADTO<PropositionDTO> {
    private boolean signatureFonds;
    private boolean signatureEntreprise;
    private Integer idPropositionDTO;
    private Integer siretEntreprise;
    private Integer siretFond;

    public NDADTOCommercialisationDTO(PropositionDTO propositionDTO, boolean signatureTasvee, boolean signatureFonds, boolean signatureEntreprise,  Integer siretEntreprise, Integer siretFond) {
        super(propositionDTO.idProposition(), propositionDTO, new Helper().siret, signatureTasvee);
        this.idPropositionDTO = propositionDTO.idProposition();
        this.signatureFonds = signatureFonds;
        this.signatureEntreprise = signatureEntreprise;
        this.siretEntreprise = siretEntreprise;
        this.siretFond = siretFond;
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

    // Getter et Setter pour siretEntreprise
    public Integer getSiretEntreprise() {
        return siretEntreprise;
    }

    public void setSiretEntreprise(Integer siretEntreprise) {
        this.siretEntreprise = siretEntreprise;
    }

    // Getter et Setter pour siretFond
    public Integer getSiretFond() {
        return siretFond;
    }

    public void setSiretFond(Integer siretFond) {
        this.siretFond = siretFond;
    }
}

