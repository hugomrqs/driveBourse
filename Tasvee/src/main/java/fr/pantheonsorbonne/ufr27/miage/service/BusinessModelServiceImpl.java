package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BusinessModelServiceImpl implements BusinessModelService{

    @Inject
    BusinessModelDAO businessModelDAO;

    //@Inject
    //smtpGateway smtp;
    @Override
    public void isFormAccepted(Integer siretStartup) {
        BusinessModelEntity businessModelEntity = businessModelDAO.createBusinessModel(siretStartup, quantificationLeveeDeFonds(), quantificationParts()) ;
        System.out.println("Le business model de la startup " + siretStartup + " à été créer avec succès, puis stocker en DB.") ;
        //SendBusinessModel(businessModelEntity, siretStartup) ;
    }

    private int quantificationLeveeDeFonds() {
        return 20 ;
    }

    private int quantificationParts() {
        return 80 ;
    }

    /*private void SendBusinessModel(BusinessModelEntity businessModelEntity, Integer siretStartup) {
        smtp.replyToOffer(businessModelEntity);
        System.out.println("Le business model de la startup " + siretStartup + " à été envoyé par mail avec succès") ;
    }*/
}
