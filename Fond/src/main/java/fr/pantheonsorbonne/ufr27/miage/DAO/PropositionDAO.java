package fr.pantheonsorbonne.ufr27.miage.DAO;

import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;

public interface PropositionDAO {

    void createNewProposition(PropositionDTO p);

    void createAcceptedProposition(PropositionDTO p);
}
