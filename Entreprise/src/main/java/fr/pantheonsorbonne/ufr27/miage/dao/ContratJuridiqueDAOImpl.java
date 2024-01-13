package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.BusinessModel;
import fr.pantheonsorbonne.ufr27.miage.dto.ContratJuridiqueBM;
import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
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
    public ContratJuridiqueBMEntity registerContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) {
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = new ContratJuridiqueBMEntity() ;
        contratJuridiqueBMEntity.setContratJuridiqueBM(contratJuridiqueBM.contratJuridiqueBM());
        contratJuridiqueBMEntity.setIdBusinessModel(getBusinessModel(contratJuridiqueBM.businessModel()));
        contratJuridiqueBMEntity.setStartUp(contratJuridiqueBM.startUp());
        contratJuridiqueBMEntity.setTasvee(contratJuridiqueBM.tasvee());
        contratJuridiqueBMEntity.setSiretTasvee(contratJuridiqueBM.siretTasvee());
        contratJuridiqueBMEntity.setPourcentageComissionTasvee(contratJuridiqueBM.pourcentageComissionTasvee());
        em.persist(contratJuridiqueBMEntity);
        return contratJuridiqueBMEntity;
    }

    @Override
    @Transactional
    public ContratJuridiqueBMEntity sign(ContratJuridiqueBMEntity contratJuridiqueBMEntity) {
        ContratJuridiqueBMEntity existingEntity = em.find(ContratJuridiqueBMEntity.class, contratJuridiqueBMEntity);
        existingEntity.setStartUp(true);
        em.merge(existingEntity);
        return existingEntity ;
    }

    private BusinessModelEntity getBusinessModel(BusinessModel businessModel){
        BusinessModelEntity existingEntity = em.find(BusinessModelEntity.class, businessModel.idBusinessModel());
        return existingEntity ;
    }
}
