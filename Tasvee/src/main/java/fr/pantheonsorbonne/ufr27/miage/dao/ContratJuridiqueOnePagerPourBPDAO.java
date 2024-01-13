package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueOnePagerPourBP;
import fr.pantheonsorbonne.ufr27.miage.model.Fond;
import fr.pantheonsorbonne.ufr27.miage.model.OnePager;

public interface ContratJuridiqueOnePagerPourBPDAO {
    void create(Boolean tasvee, Boolean fonds, String siretTasvee, Fond siretFonds, OnePager idOnPager);
    boolean isContratJuridiqueOnePagerPourBPDAOSigneByFond(int onePagerId, int siretFond);
}
