package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBPEntity;
import fr.pantheonsorbonne.ufr27.miage.model.FondEntity;
import fr.pantheonsorbonne.ufr27.miage.model.OnePagerEntity;

public interface ContratJuridiqueOnePagerPourBPDAO {
    void create(Boolean tasvee, Boolean fonds, int siretTasvee, FondEntity siretFonds, OnePagerEntity idOnPager);
    boolean isContratJuridiqueOnePagerPourBPDAOSigneByFond(int onePagerId, int siretFond);
    ContratJuridiqueOnePagerPourBPEntity selectContratJuridiqueOnePagerPourBPFromId(int id);

}
