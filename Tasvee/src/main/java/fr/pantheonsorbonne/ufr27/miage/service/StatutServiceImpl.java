package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.StatutDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
import fr.pantheonsorbonne.ufr27.miage.dto.StatutDTO;
import fr.pantheonsorbonne.ufr27.miage.model.StatutEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StatutServiceImpl implements StatutService {

    @Inject
    StatutDAO statutDAO ;

    @Override
    public StatutDTO getStatut(int idStatut) {
        System.out.println("Récupération du Statut " + idStatut + " en cours.");
        return convertToDTO(statutDAO.getStatut(idStatut)) ;
    }

    private StatutDTO convertToDTO(StatutEntity entity) {
        StatutDTO dto = new StatutDTO(entity.getNombrePart(), entity.getPrixPartActuel(), entity.getStrategieEntrepreneur());
        return dto;
    }
}
