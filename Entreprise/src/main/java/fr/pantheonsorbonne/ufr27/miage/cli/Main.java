package fr.pantheonsorbonne.ufr27.miage.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeantDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;
import fr.pantheonsorbonne.ufr27.miage.service.OfferFormEntrepriseService;
import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeantDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;
import fr.pantheonsorbonne.ufr27.miage.service.OfferFormEntrepriseService;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;

@Command
public class Main implements Runnable {

    @Inject
    OfferFormManager offerFormManager;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        // Appeler la méthode qui va travailler en arrière-plan
        offerFormManager.processOfferForm();
    }
}