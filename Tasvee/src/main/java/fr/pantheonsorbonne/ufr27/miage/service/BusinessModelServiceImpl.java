package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueBMDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
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

    private void SendBusinessModel(BusinessModelDTO businessModel, Integer siretStartup) {
        smtp.sendBusinessModelToStartUp(businessModel);
        System.out.println("Le business model de la startup " + siretStartup + " à été envoyé par mail avec succès.") ;
        System.out.println("Le destinataire du mail doit maintenant déposer le BM contenu dans le mail --> dans le dossier Entreprise/data/BM");
    }

    private BusinessModelDTO convertBMEntityToDTO(BusinessModelEntity entity) {
        return new BusinessModelDTO(entity.getIdBusinessModel(), entity.getArgentLeveeXpTasvee(), entity.getPartCedeeXpTasvee());
    }

    private void SendContratJuridiqueBM(ContratJuridiqueBMDTO contratJuridiqueBMDTO, Integer siretStartup) {
        smtp.sendContratJuridiqueBMtoStartUp(contratJuridiqueBMDTO);
        System.out.println("Le contrat juridique pour le Business Model de la startup " + siretStartup + " à été envoyé par mail avec succès") ;
        System.out.println("Le destinataire du mail doit maintenant déposer le CJ contenu dans le mail --> dans le dossier Entreprise/data/CJ");
    }

    private ContratJuridiqueBMDTO convertCJEntityToDTO(ContratJuridiqueBMEntity entity) {
        ContratJuridiqueBMDTO dto = new ContratJuridiqueBMDTO(entity.getContratJuridiqueBM(), entity.getTasvee(), entity.getStartUp(), entity.getPourcentageComissionTasvee(), entity.getSiretTasvee(), convertBMEntityToDTO(entity.getIdBusinessModel()));
        return dto;
    }

    @Override
    public void contratJuridiqueBMSigned(ContratJuridiqueBM contratJuridiqueBM) {
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = businessModelDAO.addSignature(contratJuridiqueBM) ;
        System.out.println("Le contrat juridique " + contratJuridiqueBM.contratJuridiqueBM() + " du business model signé à été receptionné, sa signature à été enregistrée avec succès en DB.");
        prestaFinancierService.requestForFinanceExpertise(contratJuridiqueBMEntity.getSiretStartUp().getIdBilanComptable().getIdBilanComptable()) ;
        prestaJuridiqueService.requestForLegalExpertise(contratJuridiqueBMEntity.getSiretStartUp().getIdStatuts().getIdStatut()) ;
    }

}
