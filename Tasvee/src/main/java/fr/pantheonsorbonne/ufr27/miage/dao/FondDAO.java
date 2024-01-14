package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.FondEntity;

public interface FondDAO {
    FondEntity selectFondBySiret(int Siret);
}
