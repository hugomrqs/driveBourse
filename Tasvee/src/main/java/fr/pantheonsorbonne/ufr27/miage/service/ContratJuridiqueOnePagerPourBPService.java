package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOProductionDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteretDTO;

public interface ContratJuridiqueOnePagerPourBPService {
    int CreateContratJuridiqueOnePagerPourBP(OnePagerInteretDTO onePagerInteretDTO);
    void SendContratJuridiqueOnePagerPourBP(int idContrat);
    void UpdateContratJuridiqueOnePagerPourBPSigne(NDADTOProductionDTO cjbp);
}
