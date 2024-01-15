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

import java.util.Scanner;


@Path("OnePager")
public class OnePagerRessource {
    @Inject
    OnePagerService onePagerService;
    @GET
    @Path("/create")
    public Response createOP() throws StartUpNotFoundException, OnePagerNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int siretEntreprise;
        while (true) {
            System.out.print("Veuillez entrer le siret de l'entreprise donc vous voulez créer le OnePager : ");
            if (scanner.hasNextInt()) {
                siretEntreprise = scanner.nextInt();
                System.out.println("Vous avez saisi le siret : " + siretEntreprise);
                break;
            } else {
                String input = scanner.next();
                System.out.println("Le siret est un entier mais vous avez saisi " + input + ". Veuillez saisir un siret valide.");
            }
        }
        onePagerService.CreateOnePager(siretEntreprise);
        return Response.status(200).entity(siretEntreprise).build();
    }

    @GET
    @Path("/sendOnePager")
    public Response sendOP() throws StartUpNotFoundException, OnePagerNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int siretEntreprise;
        while (true) {
            System.out.print("Veuillez entrer le siret de l'entreprise donc vous voulez envoyer le OnePager : ");
            if (scanner.hasNextInt()) {
                siretEntreprise = scanner.nextInt();
                System.out.println("Vous avez saisi le siret : " + siretEntreprise);
                break;
            } else {
                String input = scanner.next();
                System.out.println("Le siret est un entier mais vous avez saisi " + input + ". Veuillez saisir un siret valide.");
            }
        }
        onePagerService.sendOnePager(siretEntreprise);
        return Response.status(200).entity(siretEntreprise).build();
    }
}
