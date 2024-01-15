package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.exception.BusinessPlanNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ContractNotSignedException;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessPlanService;
import fr.pantheonsorbonne.ufr27.miage.service.OnePagerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("business-plan")
public class BusinessPlanRessource {
    @Inject
    BusinessPlanService businessPlanService;
    @GET
    @Path("/bp/{siretEntr}/{siretFond}")
    public Response businessPlan(@PathParam("siretEntr") int siretEntreprise, @PathParam("siretFond") int siretFond) throws StartUpNotFoundException, OnePagerNotFoundException, BusinessPlanNotFoundException, ContractNotSignedException {
        businessPlanService.sendBusinessPlan(siretEntreprise, siretFond);
        String output = "Paramètre reçu : siret de l'entreprise :" + siretEntreprise + " siret de Fond : "+siretFond;
        return Response.status(200).entity(output).build();
    }
}
