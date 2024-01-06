package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Statut;
//TODO : Besoin que le model JPA Status soit implémenté
import fr.pantheonsorbonne.ufr27.miage.model.StatusEntity;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class StatusServiceImpl implements StatusService {

    @Inject
    private EntityManager em;

    public Statut getStatus(int idStatus) {
        StatusEntity statusEntity = em.find(StatusEntity.class, idStatus);

        if (statusEntity != null) {
            return convertToDTO(statusEntity);
        } else {
            // Gérer le cas où le status comptable n'est pas trouvé
            return null;
        }
    }

    private Statut convertToDTO(StatusEntity entity) {
        Statut dto = new Statut(entity.nombrePart(), entity.prixPartActuel(), entity.strategieEntrepreneur());
        return dto;
    }
}
