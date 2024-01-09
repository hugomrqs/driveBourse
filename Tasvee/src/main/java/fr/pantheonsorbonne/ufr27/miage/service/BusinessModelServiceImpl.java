package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.DAO.BusinessModelDAO;
import fr.pantheonsorbonne.ufr27.miage.camel.smtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.StartUp;
import fr.pantheonsorbonne.ufr27.miage.model.Statut;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class BusinessModelServiceImpl implements  BusinessModelService {

    @Inject
    BusinessModelDAO bmDAO;

    @Inject
    smtpGateway smtp;


    @Override
    public int quantificationLeveeDeFonds(BilanComptable bilanComptable, int argentLevee) {

        int argentLeveeXpTasvee = 0;
        return argentLeveeXpTasvee;
    }

    @Override
    public int quantificationParts(Statut statuts, int partCedee) {
        int partCedeeXpTasvee = 0;
        return partCedeeXpTasvee;
    }

    @Override
    public BusinessModel CreateBusinessModel(int argentLeveeXpTasvee, int partCedeeXpTasvee, StartUp startUp) {
        BusinessModel bm = new BusinessModel();
        bm.setArgentLeveeXpTasvee(1);
        bm.setPartCedeeXpTasvee(4);
        bm.setsiretStartUp(null);
       // SendBusinessModel(bm,startUp);
        return bm;
    }
    @Transactional
    @Override
    public void SendBusinessModel(BusinessModel businessModel) {
        bmDAO.addBusinessModel(businessModel);
        smtp.replyToOffer(businessModel,"hugo.albert.marques@gmail.com");
    }

    @Override
    public void useOfferForm(OfferForm offerForm) {

    }

}
