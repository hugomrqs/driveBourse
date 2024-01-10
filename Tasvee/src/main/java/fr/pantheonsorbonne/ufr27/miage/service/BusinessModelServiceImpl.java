package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueBMDAO;
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

    //@Inject
    //smtpGateway smtp;
    @Override
    public void isFormAccepted(Integer siretStartup) {
        BusinessModelEntity businessModelEntity = businessModelDAO.createBusinessModel(siretStartup, quantificationLeveeDeFonds(), quantificationParts()) ;
        System.out.println("Le business model de la startup " + siretStartup + " à été créer avec succès, puis stocker en DB.") ;
        //SendBusinessModel(businessModelEntity, siretStartup) ;
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = contratJuridiqueBMDAO.createContratJuridiqueBM(businessModelEntity, 20) ;
        System.out.println("Le contrat juridique du business model de la startup " + siretStartup + " à été créer avec succès, puis stocker en DB.") ;
        //SendContratJuridiqueBM(contratJuridiqueBMEntity, siretStartup) ;
    }

    private int quantificationLeveeDeFonds() {
        return 20 ;
    }

    private int quantificationParts() {
        return 80 ;
    }

    /*private void SendBusinessModel(BusinessModelEntity businessModelEntity, Integer siretStartup) {
        smtp.replyToOffer(businessModelEntity); //donner un DTO pas une entity --> donc convert en DTO avant
        System.out.println("Le business model de la startup " + siretStartup + " à été envoyé par mail avec succès") ;
    }*/

    /*private void SendContratJuridiqueBM(ContratJuridiqueBMEntity contratJuridiqueBMEntity, Integer siretStartup) {
        smtp.sendContratJuridiqueBMtoStartUp(ContratJuridiqueBMEntity);
        System.out.println("Le contrat juridique pour le Business Model de la startup " + siretStartup + " à été envoyé par mail avec succès") ;
    }*/

}
