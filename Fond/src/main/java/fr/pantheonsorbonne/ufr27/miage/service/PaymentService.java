package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.NDADTOCommercialisationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.RIBDTO;

public interface PaymentService {

    void signNDACom(NDADTOCommercialisationDTO nda);

    void sendMoneyToEntrepreneur(RIBDTO rib);

    void sendMoneyToTasvee(RIBDTO rib);


}
