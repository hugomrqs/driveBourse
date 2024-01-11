package fr.pantheonsorbonne.ufr27.miage.service.smtpToStartUp;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.camel.Gateway.smtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.Statut;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class BusinessModelServiceImpl implements BusinessModelService {

    @Inject
    BusinessModelDAO bmDAO;

    @Inject
    smtpGateway smtp;


    @Override
    public int quantificationLeveeDeFonds(BilanComptable bilanComptable, int argentLevee) {

        int argentLeveeXpTasvee = 20;
        return argentLeveeXpTasvee;
    }

    @Override
    public int quantificationParts(Statut statuts, int partCedee) {
        int partCedeeXpTasvee = 80;
        return partCedeeXpTasvee;
    }



    @Override
    public BusinessModel CreateBusinessModel(BusinessModel bm,int argentLeveeXpTasvee, int partCedeeXpTasvee) {
        bm.setArgentLeveeXpTasvee(5);
        bm.setPartCedeeXpTasvee(9);
        bm.setsiretStartUp("te");

        //bm.setArgentLeveeXpTasvee(quantificationLeveeDeFonds());
        //bm.setPartCedeeXpTasvee(quanntificationParts());
       // bm.setsiretStartUp(startUp.getSiretStartUp());
        return bm;
    }


    @Transactional
    @Override
    public void SendBusinessModel(BusinessModel businessModel, int argentLeveeXpTasvee, int partCedeeXpTasvee) {
        //allerChercher en base Statut
        //BilanCOmptable
        try {
            bmDAO.addBusinessModel( CreateBusinessModel(businessModel,argentLeveeXpTasvee,partCedeeXpTasvee));

        }catch (Exception e){
            throw new RuntimeException();
        }
        smtp.replyToOffer(businessModel);
    }

    @Override
    public void useOfferForm(OfferForm offerForm) {

    }

}
