package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeant;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferForm;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;
import fr.pantheonsorbonne.ufr27.miage.model.CVDirigeantEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
public class OfferFormDAOImpl implements OfferFormDAO {
    @Inject
    EntityManager em;
    @Override
    @Transactional
    public void registerStartUpEntity(OfferForm offerForm) {
        StartUpEntity startupEntity = new StartUpEntity();
        startupEntity.setSiretStartUp(offerForm.siretStartup());
        startupEntity.setNombreDePersonne(offerForm.organigramme());
        startupEntity.setLienSiteWeb(offerForm.siteWeb());
        startupEntity.setMail(offerForm.mail());
        startupEntity.setSecteur(offerForm.secteur());
        startupEntity.setDateOfferForm(LocalDateTime.now());
        //pour les FK associés
        startupEntity.setIdBilanComptable(registerBilanComptableEntity(offerForm.bilanComptable()));
        startupEntity.setIdStatuts(registerStatutEntity(offerForm.statut()));
        startupEntity.setIdCVDirigeant(registerCVDirigeantEntity(offerForm.cvDirigeant()));
        em.persist(startupEntity);
    }

    private BilanComptableEntity registerBilanComptableEntity(BilanComptable bilanComptable) {
        BilanComptableEntity entity = new BilanComptableEntity();
        entity.setEmplois(bilanComptable.emplois());
        entity.setRessources(bilanComptable.ressources());
        entity.setVenteDeMarchandise(bilanComptable.venteDeMarchandise());
        entity.setCoutDeMarchandise(bilanComptable.coutDeMarchandise());
        em.persist(entity);
        return entity;
    }

    private StatutEntity registerStatutEntity(Statut statut) {
        StatutEntity entity = new StatutEntity();
        entity.setNombrePart(statut.nombrePart());
        entity.setPrixPartActuel(statut.prixPartActuel());
        em.persist(entity);
        return entity;
    }

    private CVDirigeantEntity registerCVDirigeantEntity(CvDirigeant cvDirigeant) {
        CVDirigeantEntity entity = new CVDirigeantEntity();
        entity.setEcole(cvDirigeant.ecole());
        entity.setEngagement(cvDirigeant.engagement());
        entity.setLienLinkedin(cvDirigeant.lienLinkedin());
        entity.setMainExperience(cvDirigeant.mainExperience());
        em.persist(entity);
        return entity ;
    }
}
