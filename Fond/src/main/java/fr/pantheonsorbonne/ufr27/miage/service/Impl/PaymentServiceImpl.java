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
    public void sendMoneyToEntrepreneur(RIBDTO rib) {
        String iban = rib.iban();
        int montant = rib.montantAPayer();
        //Faire un virement à entrepreneur
        System.out.println("Un virement de " + montant + "va être envoyé à l'iban :" + iban);
    }

    @Override
    public void sendMoneyToTasvee(RIBDTO rib){
        String iban = rib.iban();
        int montant = rib.montantAPayer();
        //Faire un virement à Tasvee
        System.out.println("Un virement de " + montant + "va être envoyé à l'iban :" + iban);
    }

}
