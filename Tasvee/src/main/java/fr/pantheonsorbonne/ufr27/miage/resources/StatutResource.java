package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;
import fr.pantheonsorbonne.ufr27.miage.service.StatutService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("statut")
public class StatutResource {

    @Inject
    private StatutService statutService;

    @Path("{idStatut}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StatutDTO getStatus(@PathParam("idStatut") int idStatut) {
        return statutService.getStatut(idStatut);
    }
}

