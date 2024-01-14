package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.BMEntity;
import fr.pantheonsorbonne.ufr27.miage.model.CJBMEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ContratJuridiqueDAOImpl implements ContratJuridiqueDAO{

    @Inject
    EntityManager em;
    @Override
    @Transactional
    public CJBMEntity registerContratJuridiqueBMInDB(ContratJuridiqueBM contratJuridiqueBM) {
        CJBMEntity cjbmEntity = new CJBMEntity(contratJuridiqueBM.tasvee(), contratJuridiqueBM.startUp(), contratJuridiqueBM.pourcentageComissionTasvee(), contratJuridiqueBM.siretTasvee(), getBusinessModel(contratJuridiqueBM.businessModel())) ;
        em.persist(cjbmEntity);
        return cjbmEntity;
    }

    @Override
    @Transactional
    public CJBMEntity sign(CJBMEntity cjbmEntity) {
        CJBMEntity existingEntity = em.find(CJBMEntity.class, cjbmEntity);
        existingEntity.setStartUp(true);
        em.merge(existingEntity);
        return existingEntity ;
    }

    private BMEntity getBusinessModel(BusinessModel businessModel){
        BMEntity existingEntity = em.find(BMEntity.class, businessModel.idBusinessModel());
        return existingEntity ;
    }
}
