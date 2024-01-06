package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dao.OfferFormDAO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import fr.pantheonsorbonne.ufr27.miage.service.OfferFormService;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;

@Path("offer-form")
public class OfferFormResource {

    @Inject
    OfferFormService offerFormService;

    //@Inject
    //BusinessModelService businessModelService;

    @Path("/new-offer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveNewOffer(OfferForm offerForm) {

        boolean isAccepted = offerFormService.isOfferAccepted(offerForm);

        if (isAccepted) {
            offerFormService.saveOfferForm(offerForm);
            //businessModelService.isFormAccepted() ; //pour trigger le service BM : trigger la composition du BM puis son envoi par SMTP
            return Response.ok("L'offre a été acceptée, le business model sera constitué puis vous sera envoyé à l'adresse mail : " + offerForm.mail()).build();
        } else {
            return Response.ok("L'offre a été reçu, cependant elle est refusée.").build();
        }
    }
}
