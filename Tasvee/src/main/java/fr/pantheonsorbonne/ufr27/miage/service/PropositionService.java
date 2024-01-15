package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;

public interface PropositionService {

    public void challengeProposal(PropositionDTO prop);

    public void addLastProposal(PropositionDTO prop);

    public void insertNDA(NDADTOCommercialisationDTO nda);


}
