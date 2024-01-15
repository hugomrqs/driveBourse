package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.OfferFormDTO;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessModelService;
import fr.pantheonsorbonne.ufr27.miage.service.OfferFormService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("offer-form")
public class OfferFormResource {

    @Inject
    OfferFormService offerFormService;

    @Inject
    BusinessModelService businessModelService;

    @Path("/new-offer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveNewOffer(OfferFormDTO offerForm) {

        System.out.println("OfferForm reçu : " + offerForm);
        boolean isAccepted = offerFormService.isOfferAccepted(offerForm);

        if (isAccepted) {
            System.out.println("Offre acceptée");
            offerFormService.saveOfferForm(offerForm);
            businessModelService.isFormAccepted(offerForm.siretStartup()) ; //pour trigger le service BM : trigger la composition du BM puis son envoi par SMTP + la composition du contratJuridiqueBM puis son envoi par SMTP
            return Response.ok("L'offre a été acceptée, le business model sera constitué puis vous sera envoyé à l'adresse mail : " + offerForm.mail()).build();
        } else {
            System.out.println("Offre refusé : objectLevee < 100000");
            return Response.ok("L'offre a été reçue, cependant elle est refusée.").build();
        }
    }
}
