package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueBMDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BusinessModelServiceImpl implements BusinessModelService{

    @Inject
    BusinessModelDAO businessModelDAO;

    @Inject
    ContratJuridiqueBMDAO contratJuridiqueBMDAO ;

    @Inject
    SmtpGateway smtp;

    @Inject
    PrestaFinancierService prestaFinancierService;

    @Inject
    PrestaJuridiqueService prestaJuridiqueService;

    @Override
    public void isFormAccepted(Integer siretStartup) {
        BusinessModelEntity businessModelEntity = businessModelDAO.createBusinessModel(siretStartup, quantificationLeveeDeFonds(), quantificationParts()) ;
        System.out.println("Le business model de la startup " + siretStartup + " à été créer avec succès, puis stocker en DB.") ;
        SendBusinessModel(convertBMEntityToDTO(businessModelEntity), siretStartup) ;
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = contratJuridiqueBMDAO.createContratJuridiqueBM(businessModelEntity, 20) ;
        System.out.println("Le contrat juridique du business model de la startup " + siretStartup + " à été créer avec succès, puis stocker en DB.") ;
        SendContratJuridiqueBM(convertCJEntityToDTO(contratJuridiqueBMEntity), siretStartup) ;
    }

    private int quantificationLeveeDeFonds() {
        return 20 ;
    }

    private int quantificationParts() {
        return 80 ;
    }

    private void SendBusinessModel(BusinessModel businessModel, Integer siretStartup) {
        smtp.sendBusinessModelToStartUp(businessModel);
        System.out.println("Le business model de la startup " + siretStartup + " à été envoyé par mail avec succès") ;
    }

    private BusinessModel convertBMEntityToDTO(BusinessModelEntity entity) {
        BusinessModel dto = new BusinessModel(entity.getIdBusinessModel(), entity.getArgentLeveeXpTasvee(), entity.getPartCedeeXpTasvee());
        return dto;
    }

    private void SendContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM, Integer siretStartup) {
        smtp.sendContratJuridiqueBMtoStartUp(contratJuridiqueBM);
        System.out.println("Le contrat juridique pour le Business Model de la startup " + siretStartup + " à été envoyé par mail avec succès") ;
    }

    private ContratJuridiqueBM convertCJEntityToDTO(ContratJuridiqueBMEntity entity) {
        ContratJuridiqueBM dto = new ContratJuridiqueBM(entity.getContratJuridiqueBM(), entity.getTasvee(), entity.getStartUp(), entity.getPourcentageComissionTasvee(), entity.getSiretTasvee(), convertBMEntityToDTO(entity.getIdBusinessModel()));
        return dto;
    }

    @Override
    public void contratJuridiqueBMSigned(ContratJuridiqueBM contratJuridiqueBM) {
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = businessModelDAO.addSignature(contratJuridiqueBM) ;
        System.out.println("Le contrat juridique " + contratJuridiqueBM.contratJuridiqueBM() + " du business model signé à été receptionné, la signature à été enregistrée avec succès en DB.");
        prestaFinancierService.requestForFinanceExpertise(contratJuridiqueBMEntity.getSiretStartUp().getIdBilanComptable().getIdBilanComptable()) ; // est-ce ok ou bien passage par dao ?
        prestaJuridiqueService.requestForLegalExpertise(contratJuridiqueBMEntity.getSiretStartUp().getIdStatuts().getIdStatut()) ;
    }

}
