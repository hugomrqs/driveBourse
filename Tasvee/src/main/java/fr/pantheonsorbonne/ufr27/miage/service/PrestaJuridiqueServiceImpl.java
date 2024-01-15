package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ExpertiseJuridiqueDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PrestaJuridiqueServiceImpl implements PrestaJuridiqueService{

    @Inject
    ExpertiseJuridiqueDAO expertiseJuridiqueDAO ;

    @Inject
    SmtpGateway smtp ;
    @Override
    public void requestForLegalExpertise(Integer idStatut) {
        smtp.askExpertJur(idStatut); //appel route smtp pour envoyer la demandeExpertiseJuridique smtp avec l'idStatut dedans + url endpoint à get http://localhost:8080/statut/{idStatut}
        System.out.println("La demande d'expertise juridique pour le statut " + idStatut + " à bien été envoyé par smtp");
        System.out.println("L'expertise juridique devra être déposée dans le dossier Tasvee/data/EJ");
    }

    @Override
    public void registerLegalExpertise(ExpertiseJuridique expertiseJuridique) {
        expertiseJuridiqueDAO.registerExpertiseJuridique(expertiseJuridique) ;
        System.out.println("L'expertise juridique du prestataire " + expertiseJuridique.siretPrestataireJuridique() + " à bien été enregistrée avec succès en DB");

    }
}
