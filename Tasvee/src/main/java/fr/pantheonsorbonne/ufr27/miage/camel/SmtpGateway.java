package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOProductionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;

public interface SmtpGateway {
    void sendCJOPBP(NDADTOProductionDTO cjopbp);

    }
