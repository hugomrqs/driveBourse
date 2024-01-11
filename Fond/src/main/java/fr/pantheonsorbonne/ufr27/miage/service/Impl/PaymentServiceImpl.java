package fr.pantheonsorbonne.ufr27.miage.service.Impl;

import fr.pantheonsorbonne.ufr27.miage.camel.MessagingGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.RIBDTO;
import fr.pantheonsorbonne.ufr27.miage.service.PaymentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PaymentServiceImpl implements PaymentService {

    @Inject
    MessagingGateway mg;

    @Override
    public void signNDACom(NDADTOCommercialisationDTO nda){
        nda.setSignatureFonds(true);
        mg.sendSignedNDACom(nda);
    }

    @Override
    public void sendMoney(RIBDTO rib, String sender){
        int montantEnvoyer = 100;
        //à la place de i faire le update dans la bdd pour que Fond s'enlève son argent
        //"UPDATE table SET argentTasvee = argentTasvee - "+rib.montantAPayer()+"



    }

}
