package fr.pantheonsorbonne.ufr27.miage.test.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeantDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferFormDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class OfferFormResourceTest {

    @Inject
    DBPopulation pop;

    TestData testData;

    @BeforeEach
    @Transactional
    public void setup() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        pop.truncateAllTables();
        testData=pop.createMaterial();
    }

    @Test
    public void testReceiveNewOfferAccepted() {
        OfferFormDTO offerForm = createAcceptedTestOfferForm();

        given()
                .contentType("application/json")
                .body(offerForm)
                .when()
                .post("/offer-form/new-offer")
                .then()
                .statusCode(200)
                .body(is("L'offre a été acceptée, le business model sera constitué puis vous sera envoyé à l'adresse mail : " + offerForm.mail()));
    }

    @Test
    public void testReceiveNewOfferRejected() {
        OfferFormDTO offerForm = createRejectedTestOfferForm();

        given()
                .contentType("application/json")
                .body(offerForm)
                .when()
                .post("/offer-form/new-offer")
                .then()
                .statusCode(200)
                .body(is("L'offre a été reçue, cependant elle est refusée."));
    }

    private OfferFormDTO createRejectedTestOfferForm() {
        BilanComptableDTO b = new BilanComptableDTO(testData.bilanComptable().getEmplois(), testData.bilanComptable().getRessources(), testData.bilanComptable().getVenteDeMarchandise(),testData.bilanComptable().getCoutDeMarchandise()) ;
        StatutDTO s = new StatutDTO(testData.statut().getNombrePart(), testData.statut().getPrixPartActuel(), testData.statut().getStrategieEntrepreneur()) ;
        CvDirigeantDTO c = new CvDirigeantDTO(testData.cvDirigeant().getEcole(), testData.cvDirigeant().getMainExperience(), testData.cvDirigeant().getLienLinkedin(),testData.cvDirigeant().getEngagement()) ;
        return new OfferFormDTO(b, s, 10, 123, 25,
                c, "lydia.com", "lydia@gmail.com", "tech") ;
    }

    private OfferFormDTO createAcceptedTestOfferForm() {
        BilanComptableDTO b = new BilanComptableDTO(testData.bilanComptable().getEmplois(), testData.bilanComptable().getRessources(), testData.bilanComptable().getVenteDeMarchandise(),testData.bilanComptable().getCoutDeMarchandise()) ;
        StatutDTO s = new StatutDTO(testData.statut().getNombrePart(), testData.statut().getPrixPartActuel(), testData.statut().getStrategieEntrepreneur()) ;
        CvDirigeantDTO c = new CvDirigeantDTO(testData.cvDirigeant().getEcole(), testData.cvDirigeant().getMainExperience(), testData.cvDirigeant().getLienLinkedin(),testData.cvDirigeant().getEngagement()) ;
        return new OfferFormDTO(b, s, 900000, 123, 25,
            c, "lydia.com", "lydia@gmail.com", "tech") ;
    }
}
