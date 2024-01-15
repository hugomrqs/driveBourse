package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BMEntity;
import fr.pantheonsorbonne.ufr27.miage.model.CJBMEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Scanner;

@Named("businessModelEntrepriseService")
@ApplicationScoped
public class BusinessModelServiceImpl implements BusinessModelService {

    @Inject
    BusinessModelDAO businessModelDAO;

    @Inject
    ContratJuridiqueDAO contratJuridiqueDAO;

    @Inject
    SmtpGateway smtp;

    @Override
    public void registerBusinessModel(BusinessModelDTO businessModel) {
        businessModelDAO.registerBusinessModelInBDD(businessModel) ;        System.out.println("le business model " + businessModel.idBusinessModel() + " à été enregistré en DB avec succès");
        System.out.println("le business model " + businessModel.idBusinessModel() + " à été enregistré en DB avec succès");
    }

    @Override
    public void registerContratJuridiqueBM(ContratJuridiqueBMDTO contratJuridiqueBMDTO) {
        CJBMEntity cjbmEntity = contratJuridiqueDAO.registerContratJuridiqueBMInDB(contratJuridiqueBMDTO) ;
        System.out.println("Le contrat juridique " + contratJuridiqueBMDTO.contratJuridiqueBM() + " du business model à été reçu et enregistré en DB avec succès.") ;
        signAndReply(cjbmEntity) ;
    }

    private void signAndReply(CJBMEntity CJBMEntity) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voulez-vous signer le contrat juridique du business model ? y/n : ");
        String response = scanner.next();
        System.out.println(response);
        if (response.equals("y")) {
            CJBMEntity updateEntity = contratJuridiqueDAO.sign(CJBMEntity);
            System.out.println("Le contrat juridique " + updateEntity.getContratJuridiqueBM() + " du business model à été signé, un champs à été modifier en DB avec succès.");
            smtp.sendSignedCJ(CJentityToDTO(updateEntity)) ;
            System.out.println("Le contrat juridique " + updateEntity.getContratJuridiqueBM() + " du business model [signé] à été renvoyé à Tasvee avec succès.");
            System.out.println("Le contrat juridique signé doit maintenant être déposée dans le dossier Tasvee/data/CJSigné");
        }
    }

    private ContratJuridiqueBMDTO CJentityToDTO(CJBMEntity cjbmEntity) {
        ContratJuridiqueBMDTO contratJuridiqueBMDTO = new ContratJuridiqueBMDTO(cjbmEntity.getContratJuridiqueBM(), cjbmEntity.getTasvee(), cjbmEntity.getStartUp(), cjbmEntity.getPourcentageComissionTasvee(), cjbmEntity.getSiretTasvee(), BMentityToDTO(cjbmEntity.getIdBusinessModel()));
        return contratJuridiqueBMDTO;
    }

    private BusinessModelDTO BMentityToDTO(BMEntity BMEntity) {
        BusinessModelDTO businessModel = new BusinessModelDTO(BMEntity.getIdBusinessModel(), BMEntity.getArgentLeveeXpTasvee(), BMEntity.getPartCedeeXpTasvee());
        return businessModel ;
    }

}
