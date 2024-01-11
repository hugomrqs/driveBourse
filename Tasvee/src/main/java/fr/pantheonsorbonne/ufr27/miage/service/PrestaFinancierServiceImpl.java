package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PrestaFinancierServiceImpl implements PrestaFinancierService{

    @Override
    public void requestForFinanceExpertise(Integer idStatut) {
        //appel route smtp pour envoyer la demande smtp avec l'idDoc dedans

    }

}
