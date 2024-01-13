package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridiqueOnePagerPourBPDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FondDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.OnePagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.NDADTODealDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.model.Fond;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

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
    public void CreateContratJuridiqueOnePagerPourBP(OnePagerInteret onePagerInteret){
        int onePagerId = onePagerInteret.idOnePager();
        OnePager onepager = onePagerDAO.selectOnePagerById(onePagerId);
        int siretFond = onePagerInteret.siretFond();
        Fond fond = fondDAO.selectFondBySiret(siretFond);
        ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP =
                new ContratJuridiqueOnePagerPourBP(true, false, 123456849,fond, onepager );
        em.persist(contratJuridiqueOnePagerPourBPDAO);
    }

    // @TODO get OnePAger par id pour creer le contrat et send avec le gateway puis récuperer le ContratSigné signé à partir d'un dossier data ecoutée a partir d'une routecmael qui le get et le register en bdd


//@TODO @Override
//    public void SendContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP cjbp) {
//        NDADTODealDTO cjbpDTO =
//         new NDADTODealDTO(
//         cjbp.getContratJuridiqueBM(),cjbp.getTasvee(),
//         cjbp.getFonds(), cjbp.getSiretTasvee(),
//         cjbp.getSiretFonds(),
//         cjbp.getIdOnPager());
//        smtp.askExpertJur(cjbpDTO);
//    }

//@TODO     @Override
//    public void RegisterContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP cjbp) {
//        cjopbp.updateContratJuridiqueOnePagerPourBP(cjbp);
//    }
}
