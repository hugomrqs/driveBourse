package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.DAO.CJOnePagerBPDAO;
import fr.pantheonsorbonne.ufr27.miage.camel.smtpGateway;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;

public class CJOnePagerBPServiceImpl implements CJOnePagerBPService {


    //importer DAO
    CJOnePagerBPDAO cjopbp;
    smtpGateway smtp;

    public void signedCJOPBP(ContratJuridiqueOnePagerPourBP cjbp){
        //cjbp.setSignature(true);
        RegisterContratJuridiqueOnePagerPourBP(cjbp);
        //createDTO
       // smtp.sendSignedCJOPBP(DTO);
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
    public void RegisterContratJuridiqueOnePagerPourBP(ContratJuridiqueOnePagerPourBP cjbp) {

        cjopbp.updateContratJuridiqueOnePagerPourBP(cjbp);
    }
}
