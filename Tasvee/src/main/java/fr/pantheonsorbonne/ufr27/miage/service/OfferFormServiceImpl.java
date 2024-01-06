package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeant;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;
import fr.pantheonsorbonne.ufr27.miage.model.CVDirigeantEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;


@ApplicationScoped
public class OfferFormServiceImpl implements OfferFormService {

    @Inject
    EntityManager em;

    @Override
    public boolean isOfferAccepted(OfferForm offerForm) {
        // Logique métier pour déterminer si l'offre est acceptée ou non
        // Exemple de logique : Si le montant de la levée est supérieur à un certain seuil, elle est acceptée
        return offerForm.objectLevee() > 100000; // À ajuster en fonction de notre logique métier
    }
    @Override
    @Transactional
    public void saveOfferForm(OfferForm offerForm) {
        StartUpEntity startupEntity = new StartUpEntity();
        startupEntity.setSiretStartUp(offerForm.siretStartup());
        startupEntity.setNombreDePersonne(offerForm.organigramme());
        startupEntity.setLienSiteWeb(offerForm.siteWeb());
        startupEntity.setMail(offerForm.mail());
        startupEntity.setSecteur(offerForm.secteur());
        startupEntity.setDateOfferForm(LocalDateTime.now());

        //pour le FK associés
        startupEntity.setIdBilanComptable(saveBilanComptableEntity(offerForm.bilanComptable()));
        startupEntity.setIdStatuts(saveStatutEntity(offerForm.statut()));
        startupEntity.setIdCVDirigeant(saveCVDirigeantEntity(offerForm.cvDirigeant()));

        em.persist(startupEntity);
    }

    private BilanComptableEntity saveBilanComptableEntity(BilanComptable bilanComptable) {
        BilanComptableEntity entity = new BilanComptableEntity();
        entity.setEmplois(bilanComptable.emplois());
        entity.setRessources(bilanComptable.ressources());
        entity.setVenteDeMarchandise(bilanComptable.venteDeMarchandise());
        entity.setCoutDeMarchandise(bilanComptable.coutDeMarchandise());
        em.persist(entity);
        return entity;
    }

    private StatutEntity saveStatutEntity(Statut statut) {
        StatutEntity entity = new StatutEntity();
        entity.setNombrePart(statut.nombrePart());
        entity.setPrixPartActuel(statut.prixPartActuel());
        em.persist(entity);
        return entity;
    }

    private CVDirigeantEntity saveCVDirigeantEntity(CvDirigeant cvDirigeant) {
        CVDirigeantEntity entity = new CVDirigeantEntity();
        entity.setEcole(cvDirigeant.ecole());
        entity.setEngagement(cvDirigeant.engagement());
        entity.setLienLinkedin(cvDirigeant.lienLinkedin());
        entity.setMainExperience(cvDirigeant.mainExperience());
        em.persist(entity);
        return entity ;
    }
}
