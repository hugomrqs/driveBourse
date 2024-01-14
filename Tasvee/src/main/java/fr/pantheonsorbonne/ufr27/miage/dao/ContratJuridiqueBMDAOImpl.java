package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;
import fr.pantheonsorbonne.ufr27.miage.model.ContratJuridiqueBMEntity;
import fr.pantheonsorbonne.ufr27.miage.model.StartUpEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ContratJuridiqueBMDAOImpl implements ContratJuridiqueBMDAO {
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public ContratJuridiqueBMEntity getContratJuridiqueBM(Integer idContratJuridiqueBM) {
        return em.find(ContratJuridiqueBMEntity.class, idContratJuridiqueBM) ;
    }

    @Override
    @Transactional
    public ContratJuridiqueBMEntity createContratJuridiqueBM(BusinessModelEntity businessModelEntity, int pourcentageComissionTasvee) {
        ContratJuridiqueBMEntity contratJuridiqueBMEntity = new ContratJuridiqueBMEntity() ;
        contratJuridiqueBMEntity.setTasvee(true);
        contratJuridiqueBMEntity.setStartUp(false); //la startup ne l'a pas encore signé
        contratJuridiqueBMEntity.setSiretTasvee(1111111);
        contratJuridiqueBMEntity.setPourcentageComissionTasvee(20);
        contratJuridiqueBMEntity.setSiretStartUp(businessModelEntity.getsiretStartUp());
        contratJuridiqueBMEntity.setIdBusinessModel(businessModelEntity);
        em.persist(contratJuridiqueBMEntity);
        return contratJuridiqueBMEntity;
    }
}
