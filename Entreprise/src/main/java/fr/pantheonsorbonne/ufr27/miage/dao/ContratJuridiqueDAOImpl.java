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
    public CJBMEntity registerContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) {
        CJBMEntity CJBMEntity = new CJBMEntity() ;
        CJBMEntity.setContratJuridiqueBM(contratJuridiqueBM.contratJuridiqueBM());
        CJBMEntity.setIdBusinessModel(getBusinessModel(contratJuridiqueBM.businessModel()));
        CJBMEntity.setStartUp(contratJuridiqueBM.startUp());
        CJBMEntity.setTasvee(contratJuridiqueBM.tasvee());
        CJBMEntity.setSiretTasvee(contratJuridiqueBM.siretTasvee());
        CJBMEntity.setPourcentageComissionTasvee(contratJuridiqueBM.pourcentageComissionTasvee());
        em.persist(CJBMEntity);
        return CJBMEntity;
    }

    @Override
    @Transactional
    public CJBMEntity sign(CJBMEntity CJBMEntity) {
        CJBMEntity existingEntity = em.find(CJBMEntity.class, CJBMEntity);
        existingEntity.setStartUp(true);
        em.merge(existingEntity);
        return existingEntity ;
    }

    private BMEntity getBusinessModel(BusinessModel businessModel){
        BMEntity existingEntity = em.find(BMEntity.class, businessModel.idBusinessModel());
        return existingEntity ;
    }
}
