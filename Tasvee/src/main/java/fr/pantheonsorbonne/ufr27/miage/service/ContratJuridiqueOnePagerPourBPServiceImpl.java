package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueOnePagerPourBPDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FondDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OnePagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.*;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.model.Fond;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
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
    public int  CreateContratJuridiqueOnePagerPourBP(OnePagerInteret onePagerInteret){
        int onePagerId = onePagerInteret.idOnePager();
        OnePager onepager = onePagerDAO.selectOnePagerById(onePagerId);
        int siretFond = onePagerInteret.siretFond();
        Fond fond = fondDAO.selectFondBySiret(siretFond);
        ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP =
                new ContratJuridiqueOnePagerPourBP(true, false, 123456849,fond, onepager );
        em.persist(contratJuridiqueOnePagerPourBP);
        return contratJuridiqueOnePagerPourBP.getContratJuridiqueBM(); //retourne l'id
    }

    @Override
    public void SendContratJuridiqueOnePagerPourBP(int idContrat) {
        ContratJuridiqueOnePagerPourBP cjbp =
                contratJuridiqueOnePagerPourBPDAO.selectContratJuridiqueOnePagerPourBPFromId(idContrat);
        ExpertiseJuridiqueDTO expertiseJuridiqueDTO = new ExpertiseJuridiqueDTO(
                 cjbp.getIdOnPager().getIdExpertiseJuridique().getNombrePartExpertise(),
                 cjbp.getIdOnPager().getIdExpertiseJuridique().getPrixPartExpertise()
                 );
         ExpertiseFinanciereDTO expertiseFinanciereDTO = new ExpertiseFinanciereDTO(
                 cjbp.getIdOnPager().getIdExpertiseFinanciere().getBFRExpert(),
                 cjbp.getIdOnPager().getIdExpertiseFinanciere().getMargeBrutExpert()
                 );
         OnePagerDTO onePagerDTO = new OnePagerDTO(
                 expertiseJuridiqueDTO,
                 expertiseFinanciereDTO,
                 cjbp.getSiretTasvee()
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
        ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP =
                contratJuridiqueOnePagerPourBPDAO.selectContratJuridiqueOnePagerPourBPFromId(cjbp.getNumeroContrat());
        if (cjbp.isSignatureFonds()){
            contratJuridiqueOnePagerPourBP.setFonds(true);
        }
        em.merge(contratJuridiqueOnePagerPourBP);
    }
}
