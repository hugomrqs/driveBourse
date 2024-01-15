package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.exception.OnePagerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.StartUpNotFoundException;

public interface OnePagerService {

    void CreateOnePager(
            int siretEntreprise) throws StartUpNotFoundException;

    void sendOnePager(int siretEntreprise) throws OnePagerNotFoundException, StartUpNotFoundException;
}
