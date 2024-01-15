package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.PropositionDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;

public interface ContratDAO {

    void insertContratTripartiteFinal(NDADTOCommercialisationDTO nda) throws StartUpNotFoundException;
}
