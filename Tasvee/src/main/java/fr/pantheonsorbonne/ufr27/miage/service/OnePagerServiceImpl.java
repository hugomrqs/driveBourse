package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.OnePagerGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ExpertiseFinanciereDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.ExpertiseJuridiqueDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OnePagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.StartUpDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.model.ExpertiseJuridique;
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
        ExpertiseFinanciere expertiseFinanciereModel = expertiseFinanciereDAO.selectExpertiseFinanicereFromSiret(siretEntreprise);
        ExpertiseJuridique expertiseJuridiqueModel = expertiseJuridiqueDAO.selectExpertiseJuridiqueFromSiret(siretEntreprise);
        onePagerDAO.createOnePager(startUpModel,expertiseJuridiqueModel, expertiseFinanciereModel);
    }
    @Override
    public void sendOnePager(int siretEntreprise) throws OnePagerNotFoundException, StartUpNotFoundException {

        fr.pantheonsorbonne.ufr27.miage.model.OnePager onePagerModel
                = onePagerDAO.selectOnePagerByIdStartUp(siretEntreprise);
        int idOnePager= onePagerModel.getIdOnePager();
        String secteur = startUpDAO.selectStartUp(siretEntreprise).getSecteur();

        ExpertiseFinanciere expertiseFinanciere = onePagerModel.getIdExpertiseFinanciere();
        fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere expertiseFinanciereDTO =
                new fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere(
                        expertiseFinanciere.getBFRExpert(),
                        expertiseFinanciere.getMargeBrutExpert()
                );

        ExpertiseJuridique expertiseJuridique = onePagerModel.getIdExpertiseJuridique();
        fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique expertiseJuridiqueDTO =
                new fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique(
                        expertiseJuridique.getNombrePartExpertise(),
                        expertiseJuridique.getPrixPartExpertise()
                );

       OnePager onePagerDTO = new OnePager(idOnePager,expertiseJuridiqueDTO,expertiseFinanciereDTO, secteur);
       onePagerGateway.sendOnePager(onePagerDTO);
    }
}
