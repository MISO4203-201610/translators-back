package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.IKnowledgeAreaLogic;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.persistence.KnowledgeAreaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class KnowledgeAreaLogic implements IKnowledgeAreaLogic {

    @Inject private KnowledgeAreaPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countKnowledgeAreas() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<KnowledgeAreaEntity> getKnowledgeAreas() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<KnowledgeAreaEntity> getKnowledgeAreas(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public KnowledgeAreaEntity getKnowledgeArea(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public KnowledgeAreaEntity createKnowledgeArea(KnowledgeAreaEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public KnowledgeAreaEntity updateKnowledgeArea(KnowledgeAreaEntity entity) {
        KnowledgeAreaEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteKnowledgeArea(Long id) {
        persistence.delete(id);
    }
}