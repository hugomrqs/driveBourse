package fr.pantheonsorbonne.ufr27.miage.dto;

public class NDADTOProductionDTO extends NDADTO<OnePagerDTO> {
    private boolean signatureFonds;
    private int siretFond;

    public NDADTOProductionDTO(int numeroContrat, OnePagerDTO onePagerDTO,
                               int siretTasvee,
                               int siretFond,
                               boolean signatureTasvee,
                               boolean signatureFonds
                               )
    {
        super(numeroContrat, onePagerDTO, siretTasvee, signatureTasvee);
        this.signatureFonds = signatureFonds;
        this.siretFond = siretFond;
    }

    public boolean isSignatureFonds() {
        return signatureFonds;
    }

    public void setSignatureFonds(boolean signatureFonds) {
        this.signatureFonds = signatureFonds;
    }
}
