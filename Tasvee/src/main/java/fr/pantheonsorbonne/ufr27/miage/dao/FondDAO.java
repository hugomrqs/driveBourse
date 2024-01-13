package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Fond;

public interface FondDAO {
    Fond selectFondBySiret(int Siret);
}
