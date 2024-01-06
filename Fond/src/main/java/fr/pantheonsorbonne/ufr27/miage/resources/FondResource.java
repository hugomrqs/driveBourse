package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("financial")
public class FondResource {

        @Inject
        protected PropositionService proposal;

        @Path("/processProposal2")
        @POST
        public boolean proposal() {
                return proposal.treatProposal();
        }

        @Path("/processProposal")
        @PUT
        @Consumes({jakarta.ws.rs.core.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public boolean counterProposal() {
                return proposal.treatProposal();
        }
}
