package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@Path("OnePager")
public class OnePagerRessource {
    @Inject
    OnePagerService onePagerService;
    @GET
    @Path("/op/{param}")
    public Response testMethod(@PathParam("param") int siretEntreprise) throws StartUpNotFoundException, OnePagerNotFoundException {
        onePagerService.sendOnePager(siretEntreprise);
        String output = "Paramètre reçu : " + siretEntreprise;
        return Response.status(200).entity(output).build();
    }
}
