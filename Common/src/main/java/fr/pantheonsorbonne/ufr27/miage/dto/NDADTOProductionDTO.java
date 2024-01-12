package fr.pantheonsorbonne.ufr27.miage.dto;

public class NDADTOProductionDTO extends NDADTO<OnePagerDTO> {
    private boolean signatureFonds;

    public NDADTOProductionDTO(OnePagerDTO onePagerDTO, boolean signatureTasvee, boolean signatureFonds) {
        super(onePagerDTO, signatureTasvee);
        this.signatureFonds = signatureFonds;
    }

    public boolean isSignatureFonds() {
        return signatureFonds;
    }

    public void setSignatureFonds(boolean signatureFonds) {
        this.signatureFonds = signatureFonds;
    }
}
