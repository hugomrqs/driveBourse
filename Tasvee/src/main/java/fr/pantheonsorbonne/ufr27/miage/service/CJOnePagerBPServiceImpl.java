package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.smtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueOnePagerPourBPRecordDTO;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.model.Fond;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;

public class CJOnePagerBPServiceImpl implements CJOnePagerBPService {


    //importer DAO
    smtpGateway smtp;
    @Override
    public ContratJuridiqueOnePagerPourBP CreateContratJuridiqueOnePagerPourBP(OnePager siretStartup, Fond fund, Boolean engagement) {
        //try catch : add
        return null;
    }

    @Override
    public void SendContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP cjbp) {
//        ContratJuridiqueOnePagerPourBPRecordDTO cjbpDTO =
//         new ContratJuridiqueOnePagerPourBPRecordDTO(
//         cjbp.getContratJuridiqueBM(),cjbp.getTasvee(),
//         cjbp.getFonds(), cjbp.getSiretTasvee(),
//         cjbp.getSiretFonds(),
//         cjbp.getIdOnPager());
//        smtp.askExpertJur(cjbpDTO);
    }

    @Override
    public void RegisterContratJuridiqueOnePagerPourBP_SigneFromFond(ContratJuridiqueOnePagerPourBP contratJuridiqueOnePagerPourBP) {

    }
}
