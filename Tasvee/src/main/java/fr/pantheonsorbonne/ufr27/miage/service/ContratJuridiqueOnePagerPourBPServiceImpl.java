package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueOnePagerPourBPDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FondDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OnePagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.*;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBPEntity;
import fr.pantheonsorbonne.ufr27.miage.model.FondEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ContratJuridiqueOnePagerPourBPServiceImpl implements ContratJuridiqueOnePagerPourBPService{
    @Inject
    ContratJuridiqueOnePagerPourBPDAO contratJuridiqueOnePagerPourBPDAO;
    @Inject
    SmtpGateway smtp;
    @Inject
    OnePagerDAO onePagerDAO;
    @Inject
    FondDAO fondDAO;
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public int  CreateContratJuridiqueOnePagerPourBP(OnePagerInteretDTO onePagerInteretDTO){
        int onePagerId = onePagerInteretDTO.idOnePager();
        OnePagerEntity onepager = onePagerDAO.selectOnePagerById(onePagerId);
        int siretFond = onePagerInteretDTO.siretFond();
        FondEntity fondEntity = fondDAO.selectFondBySiret(siretFond);
        ContratJuridiqueOnePagerPourBPEntity contratJuridiqueOnePagerPourBPEntity =
                new ContratJuridiqueOnePagerPourBPEntity(true, false, 123456849, fondEntity, onepager );
        em.persist(contratJuridiqueOnePagerPourBPEntity);
        return contratJuridiqueOnePagerPourBPEntity.getContratJuridiqueBM(); //retourne l'id
    }

    @Override
    public void SendContratJuridiqueOnePagerPourBP(int idContrat) {
        ContratJuridiqueOnePagerPourBPEntity cjbp =
                contratJuridiqueOnePagerPourBPDAO.selectContratJuridiqueOnePagerPourBPFromId(idContrat);
        ExpertiseJuridiqueDTO expertiseJuridiqueDTO = new ExpertiseJuridiqueDTO(
0,
                cjbp.getIdOnPager().getIdExpertiseJuridique().getNombrePartExpertise(),
                 cjbp.getIdOnPager().getIdExpertiseJuridique().getPrixActuelPartExpertise(),
                cjbp.getIdOnPager().getIdExpertiseJuridique().getSiretStartUp().getSiretStartUp()
                 );
         ExpertiseFinanciereDTO expertiseFinanciereDTO = new ExpertiseFinanciereDTO(
0,
                 cjbp.getIdOnPager().getIdExpertiseFinanciere().getBFRExpert(),
                 cjbp.getIdOnPager().getIdExpertiseFinanciere().getMargeBrutExpert(),
                 cjbp.getIdOnPager().getIdExpertiseJuridique().getSiretStartUp().getSiretStartUp()
         );
         OnePagerDTO onePagerDTO = new OnePagerDTO(
                 cjbp.getIdOnPager().getIdOnePager(),
                 expertiseJuridiqueDTO,
                 expertiseFinanciereDTO,
                 cjbp.getIdOnPager().getSiretStartUp().getSecteur()
         );

         NDADTOProductionDTO contratJuridiqueOnePagerPourBP = new NDADTOProductionDTO(
                 cjbp.getContratJuridiqueBM(),
                 onePagerDTO,
                 cjbp.getSiretTasvee(),
                 cjbp.getSiretFonds().getSiretFonds(),
                 cjbp.getTasvee(),
                 cjbp.getFonds()
                 );
        smtp.sendCJOPBP(contratJuridiqueOnePagerPourBP);
    }

    @Override
    public void UpdateContratJuridiqueOnePagerPourBPSigne(NDADTOProductionDTO cjbp) {
        ContratJuridiqueOnePagerPourBPEntity contratJuridiqueOnePagerPourBPEntity =
                contratJuridiqueOnePagerPourBPDAO.selectContratJuridiqueOnePagerPourBPFromId(cjbp.getNumeroContrat());
        if (cjbp.isSignatureFonds()){
            contratJuridiqueOnePagerPourBPEntity.setFonds(true);
        }
        em.merge(contratJuridiqueOnePagerPourBPEntity);
    }
}
