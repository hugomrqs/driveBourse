package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.SmtpGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.ExpertiseFinanciereDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciereDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PrestaFinancierServiceImpl implements PrestaFinancierService{

    @Inject
    ExpertiseFinanciereDAO expertiseFinanciereDAO ;

    @Inject
    SmtpGateway smtp ;

    @Override
    public void requestForFinanceExpertise(Integer idBilanComptable) {
        smtp.askExpertFin(idBilanComptable) ; //appel route smtp pour envoyer la demandeExpertiseFinanciere smtp avec l'idBilanComptable dedans + url endpoint à get http://localhost:8080/bilan-comptable/{idBilanComptable}
        System.out.println("La demande d'expertise financière pour le bilan comptable " + idBilanComptable + " à bien été envoyé par mail");
        System.out.println("L'expertise financière devra être déposée dans le dossier Tasvee/data/EF");
    }

    @Override
    public void registerFinancialExpertise(ExpertiseFinanciereDTO expertiseFinanciere) {
        expertiseFinanciereDAO.registerExpertiseFinanciere(expertiseFinanciere) ;
        System.out.println("L'expertise financière du prestataire " + expertiseFinanciere.siretPrestataireFinancier() + " à bien été enregistrée avec succès en DB");
    }
}
