package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Scanner;

@ApplicationScoped
public class BusinessModelServiceImpl implements BusinessModelService {

    @Inject
    BusinessModelDAO businessModelDAO;
    @Override
    public void registerBusinessModel(BusinessModelEntity businessModelEntity) {
        businessModelDAO.registerBusinessModel(businessModelEntity) ;
        System.out.println("le business model " + businessModelEntity.getIdBusinessModel() + " à été enregistré en DB avec succès");
    }

    @Override
    public void registerContratJuridiqueBM(ContratJuridiqueBMEntity contratJuridiqueBMEntity) {
        businessModelDAO.registerContratJuridiqueBM(contratJuridiqueBMEntity) ;
        System.out.println("Le contrat juridique " + contratJuridiqueBMEntity.getContratJuridiqueBM() + " du business model à été enregistré en DB avec succès.") ;
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
            //appel de la route SMTP pour renvoyé la updateEntity (signé). On appel grace au service correspondant
            System.out.println("Le contrat juridique " + updateEntity.getContratJuridiqueBM() + " du business model signé à été renvoyé à Tasvee avec succès.");
        }
    }

}
