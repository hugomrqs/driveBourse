package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.DAO.ContratJuridiqueDAO;
import fr.pantheonsorbonne.ufr27.miage.camel.smtpGateway;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.StartUp;
import jakarta.inject.Inject;

public class ContratJuridiqueServiceImpl implements ContratJuridiqueService {

    @Inject
    private ContratJuridiqueDAO contratJuridiqueBMDAO;

    @Inject
    smtpGateway smtp;

    @Inject
    private BusinessModelService businessModelService;

    @Override
    public ContratJuridiqueBM createContratJuridiqueBM(String siretTasvee, String siretStartUp, BusinessModel idBusinessModel,
                                                       boolean tasvee, boolean startUp, int pourcentageComissionTasvee) {
        ContratJuridiqueBM contratJuridiqueBM = new ContratJuridiqueBM();
        contratJuridiqueBM.setTasvee(tasvee);
        contratJuridiqueBM.setStartUp(startUp);
        contratJuridiqueBM.setPourcentageComissionTasvee(pourcentageComissionTasvee);
        contratJuridiqueBM.setSiretTasvee(siretTasvee);
        contratJuridiqueBM.setSiretStartUp(siretStartUp);
        contratJuridiqueBM.setIdBusinessModel(idBusinessModel);

        return contratJuridiqueBM;
    }

    @Override
    public void sendContratJuridiqueBMtoStartUp(ContratJuridiqueBM contratJuridiqueBM) {
        // Vous pouvez implémenter la logique pour envoyer le contrat juridique à la startup, par exemple, via Camel SMTP
        // Ici, j'ai assumé que vous avez une méthode sendContratJuridiqueBMtoStartUp dans votre classe smtpGateway
        // qui prend le contratJuridiqueBM et l'adresse e-mail de la startup
        smtp.sendContratJuridiqueBMtoStartUp(contratJuridiqueBM, contratJuridiqueBM.getSiretStartUp());
    }

    @Override
    public ContratJuridiqueBM registerContratJuridiqueBMSigneeFromEntrepreneur(ContratJuridiqueBM contratJuridiqueBM) {
        // Implémentez la logique pour enregistrer le contrat juridique signé dans votre système
        // Vous pouvez également ajouter d'autres traitements nécessaires
        contratJuridiqueBMDAO.addContratJuridiqueBM(contratJuridiqueBM);
        return contratJuridiqueBM;
    }
}