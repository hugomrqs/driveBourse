package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseFinanciere;
import fr.pantheonsorbonne.ufr27.miage.dto.ExpertiseJuridique;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePager;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;

public interface OnePagerService {

    void CreateOnePager(
            int siretEntreprise) throws StartUpNotFoundException;

    OnePager sendOnePager(int siretEntreprise) throws OnePagerNotFoundException, StartUpNotFoundException;
}
