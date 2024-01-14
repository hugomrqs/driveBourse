package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModelDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBMDTO;
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
    public CJBMEntity registerContratJuridiqueBM(ContratJuridiqueBMDTO contratJuridiqueBMDTO) {
        CJBMEntity CJBMEntity = new CJBMEntity() ;
        CJBMEntity.setContratJuridiqueBM(contratJuridiqueBMDTO.contratJuridiqueBM());
        CJBMEntity.setIdBusinessModel(getBusinessModel(contratJuridiqueBMDTO.businessModel()));
        CJBMEntity.setStartUp(contratJuridiqueBMDTO.startUp());
        CJBMEntity.setTasvee(contratJuridiqueBMDTO.tasvee());
        CJBMEntity.setSiretTasvee(contratJuridiqueBMDTO.siretTasvee());
        CJBMEntity.setPourcentageComissionTasvee(contratJuridiqueBMDTO.pourcentageComissionTasvee());
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

    private BMEntity getBusinessModel(BusinessModelDTO businessModel){
        BMEntity existingEntity = em.find(BMEntity.class, businessModel.idBusinessModel());
        return existingEntity ;
    }
}
