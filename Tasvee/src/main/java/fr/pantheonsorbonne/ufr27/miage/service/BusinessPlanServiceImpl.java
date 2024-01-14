package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.BusinessPlanGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BusinessPlanDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueOnePagerPourBPDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OnePagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.StartUpDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridiqueDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ContractNotSignedException;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BusinessPlanServiceImpl implements BusinessPlanService {
    @Inject
    StartUpDAO startUpDAO;
    @Inject
    OnePagerDAO onePagerDAO;
    @Inject
    BusinessPlanDAO businessPlanDAO;
    @Inject
    BusinessPlanGateway businessPlanGateway;
    @Inject
    ContratJuridiqueOnePagerPourBPDAO contratJuridiqueOnePagerPourBPDAO;

    @Override
    public void createBusinessPlan(int siretStartUp) throws StartUpNotFoundException, OnePagerNotFoundException {
        StartUpEntity startUpModel = startUpDAO.selectStartUp(siretStartUp);
        OnePagerEntity onePagerEntity = onePagerDAO.selectOnePagerByIdStartUp(siretStartUp);
        businessPlanDAO.createBusinessPlan(startUpModel, onePagerEntity);
    }

    public static String determinerSerie(int argentLeve) {
        if (argentLeve <= 100000) {
            return "pre-seed";
        } else if (argentLeve <= 500000) {
            return "seed";
        } else if (argentLeve <= 2000000) {
            return "A";
        } else if (argentLeve <= 5000000) {
            return "B";
        } else if (argentLeve <= 10000000) {
            return "C";
        } else if (argentLeve <= 20000000) {
            return "D";
        } else {
            return "E";
        }
    }

    @Override
    public void sendBusinessPlan(int siretStartUp, int siretFond) throws ContractNotSignedException, BusinessPlanNotFoundException, StartUpNotFoundException, OnePagerNotFoundException {
        BusinessPlanEntity businessPlanEntityModel = businessPlanDAO.selectBusinessPlan(siretStartUp);
        OnePagerEntity onePagerEntityModel = businessPlanEntityModel.getIdOnePager();
        StartUpEntity startUpModel = businessPlanEntityModel.getSiretStartUp();

        ExpertiseFinanciereEntity expertiseFinanciereEntityModel = onePagerEntityModel.getIdExpertiseFinanciere();

        ExpertiseFinanciereDTO expertiseFinanciereDTO =
                new fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO(
                        expertiseFinanciereEntityModel.getPrestataireFinancier().getSiretPrestataireFinancier(),
                        expertiseFinanciereEntityModel.getBFRExpert(),
                        expertiseFinanciereEntityModel.getMargeBrutExpert(),
                        expertiseFinanciereEntityModel.getSiretStartUp().getSiretStartUp()
                        );

        ExpertiseJuridiqueEntity expertiseJuridiqueEntityModel = onePagerEntityModel.getIdExpertiseJuridique();

        ExpertiseJuridiqueDTO expertiseJuridiqueDTO =
                new ExpertiseJuridiqueDTO(
                        expertiseFinanciereEntityModel.getPrestataireFinancier().getSiretPrestataireFinancier(),
                        expertiseJuridiqueEntityModel.getNombrePartExpertise(),
                        expertiseJuridiqueEntityModel.getPrixActuelPartExpertise(),
                        expertiseJuridiqueEntityModel.getSiretStartUp().getSiretStartUp()
                        );

        BusinessPlanDTO businessPlanDTO =
                new BusinessPlanDTO(
                        expertiseJuridiqueDTO,
                        expertiseFinanciereDTO,
                        startUpModel.getSiretStartUp(),
                        startUpModel.getNombreDePersonne(),
                        determinerSerie(startUpModel.getArgentLevee()),
                        startUpModel.getPartCede()
                );
        if (contratJuridiqueOnePagerPourBPDAO
                .isContratJuridiqueOnePagerPourBPDAOSigneByFond(
                        onePagerEntityModel.getIdOnePager(),
                        siretFond)) {
            businessPlanGateway.sendBusinessPlan(businessPlanDTO, siretFond);
        } else {
            throw new ContractNotSignedException(siretFond);
        }
    }
}
