package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BilanComptableDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BilanComptable;
import fr.pantheonsorbonne.ufr27.miage.model.BilanComptableEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BilanComptableServiceImpl implements BilanComptableService {

    @Inject
    BilanComptableDAO bilanComptableDAO ;

    @Override
    public BilanComptable getBilanComptable(int idBilanComptable) {
        return convertToDTO(bilanComptableDAO.getBilanComptable(idBilanComptable)) ;
    }

    private BilanComptable convertToDTO(BilanComptableEntity entity) {
        BilanComptable dto = new BilanComptable(entity.getEmplois(), entity.getRessources(), entity.getVenteDeMarchandise(), entity.getCoutDeMarchandise());
        return dto;
    }
}
