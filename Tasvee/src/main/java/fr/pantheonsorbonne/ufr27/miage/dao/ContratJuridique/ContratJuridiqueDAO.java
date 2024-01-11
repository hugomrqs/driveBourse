package fr.pantheonsorbonne.ufr27.miage.dao.ContratJuridique;

import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBM;

import java.util.List;

public interface ContratJuridiqueDAO {

    List<ContratJuridiqueBM> listAllContratJuridiqueBM();

    public ContratJuridiqueBM getContratJuridiqueBM(int contratJuridiqueBMId);

    void addContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM);

    void updateContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM);

    void deleteContratJuridiqueBM(int contratJuridiqueBMId);
}
