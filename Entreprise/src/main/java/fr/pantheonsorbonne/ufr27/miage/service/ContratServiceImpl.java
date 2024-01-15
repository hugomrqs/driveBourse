package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.MessagingGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContratServiceImpl implements ContratService{

    @Inject
    ContratDAO contrat;

    @Inject
    MessagingGateway mg;

    @Override
    public void signNDA(NDADTOCommercialisationDTO nda) {
        nda.setSignatureEntreprise(true);
        contrat.insertContrat(nda);
        mg.sendNDA(nda);
    }
}
