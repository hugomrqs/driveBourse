package fr.pantheonsorbonne.ufr27.miage.service.Impl;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOProductionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBPEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
import fr.pantheonsorbonne.ufr27.miage.service.ContratJuridiqueOnePagerPourBPService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class ContratJuridiqueOnePagerPourBPServiceImpl implements ContratJuridiqueOnePagerPourBPService {
    @Override
    public NDADTOProductionDTO Signer(NDADTOProductionDTO ndatoProductionDTO){
        ndatoProductionDTO.setSignatureFonds(true);
        return ndatoProductionDTO;
    }

}
