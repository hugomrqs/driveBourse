package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.service.StatusService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("status")
public class StatusResource {

    @Inject
    private StatusService statusService;

    @Path("{idStatus}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Statut getStatus(@PathParam("idStatus") int idStatus) {
        return statusService.getStatus(idStatus);
    }
}

