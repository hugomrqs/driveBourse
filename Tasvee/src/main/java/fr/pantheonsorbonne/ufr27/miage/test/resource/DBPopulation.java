package fr.pantheonsorbonne.ufr27.miage.test.resource;

import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;
import fr.pantheonsorbonne.ufr27.miage.model.CVDirigeantEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DBPopulation {
    public EntityManager getEm() {
        return em;
    }

    @Inject
    EntityManager em;


    @Transactional
    public void truncateAllTables() {
        // Disable referential integrity for H2
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        // Query to find all tables
        List<String> tableNames = em.createNativeQuery(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES " +
                        "WHERE TABLE_SCHEMA='PUBLIC'").getResultList();

        // Truncate each table
        for (String tableName : tableNames) {
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }

        // Re-enable referential integrity for H2
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
    @Transactional
    public TestData createMaterial() {

        StatutEntity statut = new StatutEntity(10, 10, 10);
        em.persist(statut);

        CVDirigeantEntity cvDirigeant = new CVDirigeantEntity("HEC", "Lydia", "Vincent.linkedin", true);
        em.persist(cvDirigeant);

        BilanComptableEntity bilanComptable = new BilanComptableEntity(20, 20, 20, 20);
        em.persist(bilanComptable);

        return new TestData(statut, cvDirigeant, bilanComptable);

    }


}