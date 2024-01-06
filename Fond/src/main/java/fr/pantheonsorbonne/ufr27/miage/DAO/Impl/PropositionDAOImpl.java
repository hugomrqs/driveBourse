package fr.pantheonsorbonne.ufr27.miage.DAO.Impl;

import fr.pantheonsorbonne.ufr27.miage.DAO.PropositionDAO;
import jakarta.transaction.Transactional;

import java.util.LinkedHashMap;

public class PropositionDAOImpl implements PropositionDAO {


    @Override
    @Transactional
    public void createNewInvestment() {
        /* Création du investment*/
        LinkedHashMap<String,Integer> montantBancaire = new LinkedHashMap<>();
        montantBancaire.put("Hugo",100);
        montantBancaire.put("Chadi",10);

    }


}
