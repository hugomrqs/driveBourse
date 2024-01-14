package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;

public interface PropositionDAO {
    void createNewProposition(PropositionDTO p);

    void createAcceptedProposition(PropositionDTO p);

}
