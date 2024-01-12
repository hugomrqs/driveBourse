package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BusinessPlanDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OnePagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.StartUpDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BusinessPlanServiceImpl implements BusinessPlanService{
    @Inject
    StartUpDAO startUpDAO;
    @Inject
    OnePagerDAO onePagerDAO;
    @Inject
    BusinessPlanDAO businessPlanDAO;
    @Override
    public void createBusinessPlan(int siretStartUp) throws StartUpNotFoundException, OnePagerNotFoundException {
        StartUpEntity startUpModel = startUpDAO.selectStartUp(siretStartUp);
        OnePager onePager = onePagerDAO.selectOnePagerByIdStartUp(siretStartUp);
        businessPlanDAO.createBusinessPlan(startUpModel,onePager);
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
    public void sendBusinessPlan(int siretStartUp) throws BusinessPlanNotFoundException, StartUpNotFoundException, OnePagerNotFoundException {
        BusinessPlan businessPlanModel = businessPlanDAO.selectBusinessPlan(siretStartUp);
        OnePager onePagerModel = businessPlanModel.getIdOnePager();
        StartUpEntity startUpModel = businessPlanModel.getSiretStartUp();

        ExpertiseFinanciere expertiseFinanciereModel = onePagerModel.getIdExpertiseFinanciere();

        fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere expertiseFinanciereDTO =
                new fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere(
                        expertiseFinanciereModel.getBFRExpert(),
                        expertiseFinanciereModel.getMargeBrutExpert()
                );

        ExpertiseJuridique expertiseJuridiqueModel = onePagerModel.getIdExpertiseJuridique();

        fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique expertiseJuridiqueDTO =
                new fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique(
                        expertiseJuridiqueModel.getNombrePartExpertise(),
                        expertiseJuridiqueModel.getPrixPartExpertise()
                );

        fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlan businessPlan =
                new fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlan(
                        expertiseJuridiqueDTO,
                        expertiseFinanciereDTO,
                        startUpModel.getSiretStartUp(),
                        startUpModel.getNombreDePersonne(),
                        determinerSerie(startUpModel.getArgentLevee()),
                        startUpModel.getPartCede()
                );
        //@TODO if ContratJurdiqueOnePagerPourBP is signed (appel a ContratJurdiqueOnePagerPourBPDAO) => send to BusinessPlanGateway donnée cryptés et la clé c'est l'id du NDa quel génie
    }
}
