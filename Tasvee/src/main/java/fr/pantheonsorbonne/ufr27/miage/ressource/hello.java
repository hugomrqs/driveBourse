package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.camel.smtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.service.OfferService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
@Path("hello")
public class hello {

    smtpGateway smtp;

        @Path("/test")
        @POST
        public void proposal() {
            BusinessModel bm = new BusinessModel();
            bm.setPartCedeeXpTasvee(1);
            bm.setArgentLeveeXpTasvee(30);
            smtp.replyToOffer(bm,"hugo.marques@etu.univ-paris1","hugo.albert.gmail.com");
        }
}
