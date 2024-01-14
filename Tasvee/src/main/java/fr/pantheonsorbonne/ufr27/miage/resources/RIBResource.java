package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.PropositionGateway;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
