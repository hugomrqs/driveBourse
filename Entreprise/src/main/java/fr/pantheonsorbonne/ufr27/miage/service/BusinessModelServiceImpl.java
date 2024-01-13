package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Scanner;

@ApplicationScoped
public class BusinessModelServiceImpl implements BusinessModelService {

    @Inject
    BusinessModelDAO businessModelDAO;

    @Inject
    SmtpGateway smtp;
    @Override
    public void registerBusinessModel(BusinessModel businessModel) {
        businessModelDAO.registerBusinessModel(businessModel) ;
        System.out.println("le business model " + businessModel.idBusinessModel() + " à été enregistré en DB avec succès");
    }

    @Override
    public void registerContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) {
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = businessModelDAO.registerContratJuridiqueBM(contratJuridiqueBM) ;
        System.out.println("Le contrat juridique " + contratJuridiqueBM.contratJuridiqueBM() + " du business model à été reçu et enregistré en DB avec succès.") ;
        signAndReply(contratJuridiqueBMEntity) ;
    }

    private void signAndReply(ContratJuridiqueBMEntity contratJuridiqueBMEntity) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voulez-vous signer le contrat juridique du business model ? y/n : ");
        String response = scanner.next();
        System.out.println(response);
        if (response.equals("y")) {
            ContratJuridiqueBMEntity updateEntity = businessModelDAO.sign(contratJuridiqueBMEntity);
            System.out.println("Le contrat juridique " + updateEntity.getContratJuridiqueBM() + " du business model à été signé, un champs à été modifier en DB avec succès.");
            smtp.sendSignedCJ(updateEntity) ;
            System.out.println("Le contrat juridique " + updateEntity.getContratJuridiqueBM() + " du business model signé à été renvoyé à Tasvee avec succès.");
        }
    }

}
