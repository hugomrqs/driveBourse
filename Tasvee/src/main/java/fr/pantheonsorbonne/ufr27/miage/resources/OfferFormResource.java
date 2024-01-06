package fr.pantheonsorbonne.ufr27.miage.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import fr.pantheonsorbonne.ufr27.miage.service.OfferFormService;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;

@Path("offer-form")
public class OfferFormResource {

    @Inject
    private OfferFormService offerFormService;

    @Path("/new-offer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveNewOffer(OfferForm offerForm) {
        //Logique à la reception d'une offer
        //Par exemple, save en DB l'offer : offerFormService.saveOfferForm(offerForm);

        boolean isAccepted = offerFormService.isOfferAccepted(offerForm);

        if (isAccepted) {
            return Response.ok("L'offre a été acceptée.").build();
        } else {
            return Response.ok("L'offre a été refusée.").build();
        }
    }
}
