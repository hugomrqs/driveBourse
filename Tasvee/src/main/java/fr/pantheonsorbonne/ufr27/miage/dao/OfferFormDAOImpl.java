package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.CvDirigeantDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.OfferFormDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;
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
    public void registerStartUpEntity(OfferFormDTO offerForm) {
        StartUpEntity startupEntity = new StartUpEntity();
        startupEntity.setSiretStartUp(offerForm.siretStartup());
        startupEntity.setNombreDePersonne(offerForm.organigramme());
        startupEntity.setLienSiteWeb(offerForm.siteWeb());
        startupEntity.setMail(offerForm.mail());
        startupEntity.setSecteur(offerForm.secteur());
        startupEntity.setDateOfferForm(LocalDateTime.now());
        //pour les FK associés
        startupEntity.setIdBilanComptable(registerBilanComptableEntity(offerForm.bilanComptableDTO()));
        startupEntity.setIdStatuts(registerStatutEntity(offerForm.statutDTO()));
        startupEntity.setIdCVDirigeant(registerCVDirigeantEntity(offerForm.cvDirigeant()));
        em.persist(startupEntity);
    }

    private BilanComptableEntity registerBilanComptableEntity(BilanComptableDTO bilanComptable) {
        BilanComptableEntity entity = new BilanComptableEntity();
        entity.setEmplois(bilanComptable.emplois());
        entity.setRessources(bilanComptable.ressources());
        entity.setVenteDeMarchandise(bilanComptable.venteDeMarchandise());
        entity.setCoutDeMarchandise(bilanComptable.coutDeMarchandise());
        em.persist(entity);
        return entity;
    }

    private StatutEntity registerStatutEntity(StatutDTO statut) {
        StatutEntity entity = new StatutEntity();
        entity.setNombrePart(statut.nombrePart());
        entity.setPrixPartActuel(statut.prixPartActuel());
        em.persist(entity);
        return entity;
    }

    private CVDirigeantEntity registerCVDirigeantEntity(CvDirigeantDTO cvDirigeant) {
        CVDirigeantEntity entity = new CVDirigeantEntity();
        entity.setEcole(cvDirigeant.ecole());
        entity.setEngagement(cvDirigeant.engagement());
        entity.setLienLinkedin(cvDirigeant.lienLinkedin());
        entity.setMainExperience(cvDirigeant.mainExperience());
        em.persist(entity);
        return entity ;
    }
}
