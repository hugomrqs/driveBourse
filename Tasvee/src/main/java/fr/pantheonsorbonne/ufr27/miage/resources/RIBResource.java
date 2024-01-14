package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.PropositionGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.OfferFormDAO;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import fr.pantheonsorbonne.ufr27.miage.service.OfferFormService;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferFormDTO;

@Path("rib")
public class RIBResource {


    @Inject
    PropositionGateway pg;

    @Path("/sendRIB/{ribEntrepreneur}/{ribTasvee}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void receiveNewOffer(@PathParam("ribEntrepreneur") Integer ribEntrepreneur, @PathParam("ribTasvee") Integer ribTasvee) {
        pg.sendRIB(ribEntrepreneur,ribTasvee);
    }
}
