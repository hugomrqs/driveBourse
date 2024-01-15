package fr.pantheonsorbonne.ufr27.miage.DAO;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessPlanDTO;
import fr.pantheonsorbonne.ufr27.miage.model.PropositionEntity;

public interface BusinessDAO {

    void createNewBusinessPlan(BusinessPlanDTO bp);

    PropositionEntity createProposition(BusinessPlanDTO bp);

}
