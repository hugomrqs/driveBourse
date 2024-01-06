package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
//TODO : Besoin que le model JPA BilanComptable soit implémenté
import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class BilanComptableServiceImpl {

    @Inject
    private EntityManager em;

    public BilanComptable getBilanComptable(int idBilanComptable) {
        BilanComptableEntity bilanComptableEntity = em.find(BilanComptableEntity.class, idBilanComptable);

        if (bilanComptableEntity != null) {
            return convertToDTO(bilanComptableEntity);
        } else {
            // Gérer le cas où le bilan comptable n'est pas trouvé
            return null;
        }
    }

    private BilanComptable convertToDTO(BilanComptableEntity entity) {
        BilanComptable dto = new BilanComptable(entity.getEmplois(), entity.getRessources(), entity.getVenteDeMarchandise(), entity.CoutDeMarchandise());
        return dto;
    }
}
