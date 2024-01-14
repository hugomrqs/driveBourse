package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.OnePagerGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ExpertiseFinanciereDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.ExpertiseJuridiqueDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OnePagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.StartUpDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciereEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridiqueEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OnePagerServiceImpl implements OnePagerService {
    @Inject
    StartUpDAO startUpDAO;
    @Inject
    OnePagerDAO onePagerDAO;
    @Inject
    ExpertiseJuridiqueDAO expertiseJuridiqueDAO;
    @Inject
    ExpertiseFinanciereDAO expertiseFinanciereDAO;
    @Inject
    OnePagerGateway onePagerGateway;


    @Override
    public void CreateOnePager(
            int siretEntreprise)
            throws StartUpNotFoundException {
        StartUpEntity startUpModel = startUpDAO.selectStartUp(siretEntreprise);
        ExpertiseFinanciereEntity expertiseFinanciereEntityModel = expertiseFinanciereDAO.selectExpertiseFinanicereFromSiret(siretEntreprise);
        ExpertiseJuridiqueEntity expertiseJuridiqueEntityModel = expertiseJuridiqueDAO.selectExpertiseJuridiqueFromSiret(siretEntreprise);
        onePagerDAO.createOnePager(startUpModel, expertiseJuridiqueEntityModel, expertiseFinanciereEntityModel);
    }
    @Override
    public void sendOnePager(int siretEntreprise) throws OnePagerNotFoundException, StartUpNotFoundException {

        OnePagerEntity onePagerEntityModel
                = onePagerDAO.selectOnePagerByIdStartUp(siretEntreprise);
        int idOnePager= onePagerEntityModel.getIdOnePager();
        String secteur = startUpDAO.selectStartUp(siretEntreprise).getSecteur();

        ExpertiseFinanciereEntity expertiseFinanciereEntity = onePagerEntityModel.getIdExpertiseFinanciere();
        ExpertiseFinanciereDTO expertiseFinanciereDTO =
                new ExpertiseFinanciereDTO(
                        expertiseFinanciereEntity.getPrestataireFinancier().getSiretPrestataireFinancier(),
                        expertiseFinanciereEntity.getBFRExpert(),
                        expertiseFinanciereEntity.getMargeBrutExpert(),
                        expertiseFinanciereEntity.getIdExpertiseFinanciere()
                );

        ExpertiseJuridiqueEntity expertiseJuridiqueEntity = onePagerEntityModel.getIdExpertiseJuridique();
        ExpertiseJuridiqueDTO expertiseJuridiqueDTO =
                new ExpertiseJuridiqueDTO(
                        expertiseJuridiqueEntity.getPrestataireJuridique().getSiretPrestataireJuridique(),
                        expertiseJuridiqueEntity.getNombrePartExpertise(),
                        expertiseJuridiqueEntity.getPrixActuelPartExpertise(),
                        expertiseJuridiqueEntity.getIdExpertiseJuridique()
                );

       OnePagerDTO onePagerDTO = new OnePagerDTO(idOnePager,expertiseJuridiqueDTO,expertiseFinanciereDTO, secteur);
       onePagerGateway.sendOnePager(onePagerDTO);
    }
}
