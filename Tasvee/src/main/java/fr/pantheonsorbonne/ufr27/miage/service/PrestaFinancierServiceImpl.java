package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.ExpertiseFinanciereDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PrestaFinancierServiceImpl implements PrestaFinancierService{

    @Inject
    ExpertiseFinanciereDAO expertiseFinanciereDAO ;

    @Override
    public void requestForFinanceExpertise(Integer idBilanComptable) {
        //appel route smtp pour envoyer la demande smtp avec l'idBilanComptable dedans + url endpoint à get http://localhost:8080/bilan-comptable/{idBilanComptable}
        System.out.println("La demande d'expertise financière pour le bilan comptable " + idBilanComptable + " à bien été envoyé par smtp");
    }

    @Override
    public void registerFinancialExpertise(ExpertiseFinanciere expertiseFinanciere) {
        expertiseFinanciereDAO.registerExpertiseFinanciere(expertiseFinanciere) ;
        System.out.println("L'expertise financière du prestataire " + expertiseFinanciere.siretPrestataireFinancier() + " à bien été enregistrée avec succès en DB");

    }
}
