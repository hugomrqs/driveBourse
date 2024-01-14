package fr.pantheonsorbonne.ufr27.miage.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeant;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.service.OfferFormEntrepriseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.InputMismatchException;
import java.util.Scanner;

@ApplicationScoped
public class OfferFormManager {

    @Inject
    OfferFormEntrepriseService offerFormEntrepriseService;

    public void processOfferForm() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Voulez-vous créer un offerForm ? y/n : ");
            if (scanner.hasNext()) {
                String response = scanner.next();
                System.out.println(response);

                if ("y".equals(response)) {
                    // Lire les valeurs du DTO depuis le terminal

                    // DTO bilanComptable
                    System.out.println("Composition du Bilan comptable : ");

                    int emplois = readIntWithPrompt(" 1. Entrez la valeur pour emplois : ", scanner);
                    int ressources = readIntWithPrompt(" 2. Entrez la valeur pour ressources : ", scanner);
                    int venteDeMarchandise = readIntWithPrompt(" 3. Entrez la valeur pour venteDeMarchandise : ", scanner);
                    int coutDeMarchandise = readIntWithPrompt(" 4. Entrez la valeur pour coutDeMarchandise : ", scanner);

                    BilanComptable bilanComptable = new BilanComptable(emplois, ressources, venteDeMarchandise, coutDeMarchandise);

                    // DTO statut
                    System.out.println("Composition du Statut : ");

                    int nombrePart = readIntWithPrompt(" 1. Entrez la valeur pour nombrePart : ", scanner);
                    int prixPartActuel = readIntWithPrompt(" 2. Entrez la valeur pour prixPartActuel : ", scanner);
                    int strategieEntrepreneur = readIntWithPrompt(" 3. Entrez la valeur pour strategieEntrepreneur : ", scanner);

                    Statut statut = new Statut(nombrePart, prixPartActuel, strategieEntrepreneur);

                    // DTO cvDirigeant
                    System.out.println("Composition du CVDirigeant : ");

                    String ecole = readStringWithPrompt(" 1. Entrez la valeur pour ecole : ", scanner);
                    String mainExperience = readStringWithPrompt(" 2. Entrez la valeur pour mainExperience : ", scanner);
                    String lienLinkedin = readStringWithPrompt(" 3. Entrez la valeur pour lienLinkedin : ", scanner);
                    boolean engagementRSE = readBooleanWithPrompt(" 4. Entrez la valeur pour engagementRSE : ", scanner);

                    CvDirigeant cvDirigeant = new CvDirigeant(ecole, mainExperience, lienLinkedin, engagementRSE);

                    // Attributs simples de l'offerForm
                    System.out.println("Composition des informations générales : ");

                    int objectLevee = readIntWithPrompt(" 1. Entrez la valeur pour objectLevee : ", scanner);
                    Integer siretStartup = readIntWithPrompt(" 2. Entrez la valeur pour siretStartup : ", scanner);
                    int organigramme = readIntWithPrompt(" 3. Entrez la valeur pour organigramme : ", scanner);
                    String siteWeb = readStringWithPrompt(" 4. Entrez la valeur pour siteWeb : ", scanner);
                    String mail = readStringWithPrompt(" 5. Entrez la valeur pour mail : ", scanner);
                    String secteur = readStringWithPrompt(" 6. Entrez la valeur pour secteur : ", scanner);

                    System.out.print("Voulez-vous envoyer l'offerForm ? y/n : ");
                    if (scanner.hasNext()) {
                        response = scanner.next();
                        System.out.println(response);
                        if ("y".equals(response)) {
                            // Appelez la fonction createAndSendOfferForm du service
                            offerFormEntrepriseService.createAndSendOfferForm(bilanComptable, statut, objectLevee, siretStartup,
                                    organigramme, cvDirigeant, siteWeb, mail, secteur);
                            // Stocker en DB BilanComptable / Statut / CVdirigeant
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermer le scanner pour éviter les fuites de ressources
            if (scanner != null) {
                scanner.close();
            }
        }
        // Ajoutez une attente infinie pour maintenir l'application active
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour lire un entier avec un message de prompt et gérer les erreurs
    private int readIntWithPrompt(String prompt, Scanner scanner) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                System.out.println(value);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer un nombre entier.");
                scanner.next();  // Pour consommer la mauvaise entrée du scanner
            }
        }

        return value;
    }

    // Méthode utilitaire pour lire une chaîne avec un message de prompt
    private String readStringWithPrompt(String prompt, Scanner scanner) {
        System.out.print(prompt);
        String value = scanner.next() ;
        System.out.println(value);
        return value;
    }

    // Méthode utilitaire pour lire un booléen avec un message de prompt et gérer les erreurs
    private boolean readBooleanWithPrompt(String prompt, Scanner scanner) {
        boolean value = false;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                value = scanner.nextBoolean();
                System.out.println(value);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer true ou false.");
                scanner.next();  // Pour consommer la mauvaise entrée du scanner
            }
        }

        return value;
    }
}