package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.StatutDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StatutServiceImpl implements StatutService {

    @Inject
    StatutDAO statutDAO ;

    @Override
    public Statut getStatut(int idStatut) {
        return convertToDTO(statutDAO.getStatut(idStatut)) ;
    }

    private Statut convertToDTO(StatutEntity entity) {
        Statut dto = new Statut(entity.getNombrePart(), entity.getPrixPartActuel(), entity.getStrategieEntrepreneur());
        return dto;
    }
}
