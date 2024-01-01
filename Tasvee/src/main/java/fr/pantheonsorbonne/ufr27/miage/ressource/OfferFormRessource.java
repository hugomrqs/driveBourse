package fr.pantheonsorbonne.ufr27.miage.ressource;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/submit-offer")

public class OfferFormRessource {


        @POST
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        public Response submitOffer(OfferForm offerForm) {
            // Traiter les données du formulaire
            // ...

            return Response.ok().entity("Offre soumise avec succès!").build();
        }
    }

