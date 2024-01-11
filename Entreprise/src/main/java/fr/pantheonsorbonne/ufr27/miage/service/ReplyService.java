package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.BusinessModelEntity;

public interface ReplyService {

    void registerBusinessModel(BusinessModelEntity bm);

    void signerCJBM();

}
