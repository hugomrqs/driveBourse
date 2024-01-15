package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.service.BilanComptableService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("bilan-comptable")
public class BilanComptableResource {

    @Inject
    BilanComptableService bilanComptableService;


    @Path("/{idBilanComptable}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BilanComptableDTO getBilanComptable(@PathParam("idBilanComptable") int idBilanComptable) {
        return bilanComptableService.getBilanComptable(idBilanComptable);
    }
}
