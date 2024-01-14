package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.PropositionGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.RIBDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("rib")
public class RIBResource {


    @Inject
    PropositionGateway pg;

    @Path("/sendRIB/{ibanEntrepreneur}/{montantEntrepreneur}/{ibanTasvee}/{montantTasvee}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void receiveNewOffer(@PathParam("ibanEntrepreneur") String ibanEntrepreneur,
                                @PathParam("montantEntrepreneur") Integer montantEntrepreneur,
                                @PathParam("ibanTasvee") String ibanTasvee,
                                @PathParam("montantTasvee") Integer montantTasvee) {
        RIBDTO ribEnt = new RIBDTO(ibanEntrepreneur,montantEntrepreneur);
        RIBDTO ribTasvee = new RIBDTO(ibanTasvee,montantTasvee);
        pg.sendRIB(ribEnt,ribTasvee);
    }
}
