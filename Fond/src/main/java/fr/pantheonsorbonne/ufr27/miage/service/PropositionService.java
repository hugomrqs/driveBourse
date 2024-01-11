package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;

public interface PropositionService {

    /* Renvoie true si la proposition est accepté et false sinon */
    public boolean challengeProposal(PropositionDTO prop);

    public void addLastProposal(PropositionDTO prop);

}
