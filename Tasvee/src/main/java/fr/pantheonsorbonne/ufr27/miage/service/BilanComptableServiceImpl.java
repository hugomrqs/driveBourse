package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BilanComptableDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptableDTO;
import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BilanComptableServiceImpl implements BilanComptableService {

    @Inject
    BilanComptableDAO bilanComptableDAO ;

    @Override
    public BilanComptableDTO getBilanComptable(int idBilanComptable) {
        System.out.println("Récupération du Bilan Comptable " + idBilanComptable + " en cours.");
        return convertToDTO(bilanComptableDAO.getBilanComptable(idBilanComptable)) ;
    }

    private BilanComptableDTO convertToDTO(BilanComptableEntity entity) {
        BilanComptableDTO dto = new BilanComptableDTO(entity.getEmplois(), entity.getRessources(), entity.getVenteDeMarchandise(), entity.getCoutDeMarchandise());
        return dto;
    }
}
