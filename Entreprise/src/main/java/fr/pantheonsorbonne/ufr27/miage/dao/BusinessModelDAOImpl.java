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
public class BusinessModelDAOImpl implements BusinessModelDAO{
    @Inject
    EntityManager em;
    @Override
    @Transactional
    public void registerBusinessModel(BusinessModel businessModel) {
        BusinessModelEntity businessModelEntity = new BusinessModelEntity() ;
        businessModelEntity.setIdBusinessModel(businessModel.idBusinessModel());
        businessModelEntity.setArgentLeveeXpTasvee(businessModel.argentLeveeXpTasvee());
        businessModelEntity.setPartCedeeXpTasvee(businessModel.partCedeeXpTasvee());
        em.persist(businessModelEntity);
    }

    @Override
    @Transactional
    public ContratJuridiqueBMEntity registerContratJuridiqueBM(ContratJuridiqueBM contratJuridiqueBM) {
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = new ContratJuridiqueBMEntity() ;
        contratJuridiqueBMEntity.setContratJuridiqueBM(contratJuridiqueBM.contratJuridiqueBM());
        contratJuridiqueBMEntity.setIdBusinessModel(contratJuridiqueBM.idBusinessModel());
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
}
