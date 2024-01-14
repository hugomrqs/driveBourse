package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOProductionDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;

public interface ContratJuridiqueOnePagerPourBPService {
    int CreateContratJuridiqueOnePagerPourBP(OnePagerInteret onePagerInteret);
    void SendContratJuridiqueOnePagerPourBP(int idContrat);
    void UpdateContratJuridiqueOnePagerPourBPSigne(NDADTOProductionDTO cjbp);
}
