package fr.pantheonsorbonne.ufr27.miage.ressource;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.service.BusinessModelService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("hello")
public class hello {

    @Inject
    BusinessModelService bmS;


        @Path("/test")
        @POST
        public void proposal() {
            System.out.println("test");
            BusinessModel bm = new BusinessModel();
            bm.setPartCedeeXpTasvee(1);
            bm.setArgentLeveeXpTasvee(30);
            bm.setsiretStartUp(null);
            bmS.SendBusinessModel(bm);
        }

    @POST
    @Path("/{param}")
    public Response testMethod(@PathParam("param") int param) {
        BusinessModel bm = new BusinessModel();
        bm.setPartCedeeXpTasvee(1);
        bm.setArgentLeveeXpTasvee(30);
        bm.setsiretStartUp(null);
        bmS.SendBusinessModel(bm);
        String output = "Paramètre reçu : " + param;
        return Response.status(200).entity(output).build();
    }
}
