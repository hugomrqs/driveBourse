package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.MessagingGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.*;
import fr.pantheonsorbonne.ufr27.miage.model.Proposition;
import fr.pantheonsorbonne.ufr27.miage.service.PropositionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("financial")
public class FondResource {

        @Inject
        protected PropositionService proposal;

        @Inject
        protected MessagingGateway mg;


        @Path("/sendProposal")
        @GET
        @Consumes({jakarta.ws.rs.core.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public void processProposal() {
                PropositionDTO prop = new PropositionDTO(1,10000,25,123456,false);
                mg.sendProposal(prop);
        }

        @Path("/counterProposal")
        @GET
        @Consumes({jakarta.ws.rs.core.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public boolean counterProposal() {
                PropositionDTO prop = new PropositionDTO(12,10000,25,123456,false);
                return proposal.challengeProposal(prop);
        }


        @Path("/testSendBp")
        @GET
        @Consumes({jakarta.ws.rs.core.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public void sendBp() {
                ExpertiseFinanciereDTO ef = new ExpertiseFinanciereDTO(100000,1000);
                ExpertiseJuridiqueDTO ej = new ExpertiseJuridiqueDTO(5, 500);
                OrganigrammeDTO o = new OrganigrammeDTO(10);
                BusinessPlanDTO bp = new BusinessPlanDTO(ej,ef,1,o,"www.zara.com");
                mg.testEnvoiBP(bp);
        }

}
