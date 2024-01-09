package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeant;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class OfferFormServiceImpl implements OfferFormService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferFormServiceImpl.class);
    private static final String API_ENDPOINT = "http://localhost:8080/offer-form/new-offer";

    @Override
    public void createAndSendOfferForm(BilanComptable bilanComptable, Statut statut, int objectLevee, Integer siretStartup,
                                       int organigramme, CvDirigeant cvDirigeant, String siteWeb, String mail, String secteur) {
        // Construire le DTO OfferForm
        OfferForm offerForm = new OfferForm(bilanComptable, statut, objectLevee, siretStartup, organigramme, cvDirigeant, siteWeb, mail, secteur);

        // Convertir l'objet DTO en JSON
        try {
            String jsonOfferForm = convertToJson(offerForm);

            // Envoyer le JSON à l'API
            sendJsonToApi(jsonOfferForm);
            System.out.println("json envoyé sur le endpoint de Tasvee : " + jsonOfferForm);
        } catch (IOException e) {
            LOGGER.error("Erreur lors de la conversion en JSON", e);
        }
    }

    private String convertToJson(Object object) throws IOException {
        return new ObjectMapper().writeValueAsString(object);
    }

    private void sendJsonToApi(String json) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(API_ENDPOINT);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes());
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                LOGGER.info("L'offre a été envoyée avec succès.");
            } else {
                LOGGER.error("Erreur lors de l'envoi de l'offre. Code de réponse : {}", responseCode);
            }
        } catch (IOException e) {
            LOGGER.error("Erreur lors de l'envoi de l'offre", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
