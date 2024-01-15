package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.PropositionGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.RIBDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Scanner;

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
        System.out.print("Vous allez rentrer l'iban et le montant à virer à l'entreprise et à Tasvee");
        Scanner scanner = new Scanner(System.in);
        RIBDTO ribTasvee;
        RIBDTO ribEnt;
        System.out.print("Veuillez entrer l'iban de l'entreprise : ");
        String ibanEnt = scanner.next();
        int montantEntre;
        while (true) {
            System.out.print("Veuillez entrer le montant à virer sur le compte de l'entreprise");
            if (scanner.hasNextInt()) {
                montantEntre = scanner.nextInt();
                ribEnt = new RIBDTO(ibanEnt, montantEntre);
                break;
            } else {
                String input = scanner.next();
                System.out.println("Le montant doit être un entier mais vous avez saisi " + input + ". Veuillez saisir un montant valide.");
            }
        }
        System.out.print("Veuillez entrer l'iban de Tasvee : ");
        String ibanTas = scanner.next();
        int montantTas;
        while (true) {
            System.out.print("Veuillez entrer le montant à virer sur le compte de Tasvee");
            if (scanner.hasNextInt()) {
                montantTas = scanner.nextInt();
                ribTasvee = new RIBDTO(ibanTas, montantTas);
                break;
            } else {
                String input = scanner.next();
                System.out.println("Le montant doit être un entier mais vous avez saisi " + input + ". Veuillez saisir un montant valide.");
            }
        }
        pg.sendRIB(ribEnt, ribTasvee);
    }
}
