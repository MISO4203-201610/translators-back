package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.IKnowledgeAreaLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslationRequestPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class TranslationRequestLogic implements ITranslationRequestLogic {

    @Inject private TranslationRequestPersistence persistence;
    
    @Inject private IKnowledgeAreaLogic knowledgeAreaLogic;

    /**
     * @generated
     */
    @Override
    public int countTranslationRequests() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslationRequestEntity> getTranslationRequests() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslationRequestEntity> getTranslationRequests(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public TranslationRequestEntity getTranslationRequest(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public TranslationRequestEntity createTranslationRequest(TranslationRequestEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public TranslationRequestEntity updateTranslationRequest(TranslationRequestEntity entity) {
        TranslationRequestEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTranslationRequest(Long id) {
        persistence.delete(id);
    }
    
     @Override
    public List<KnowledgeAreaEntity> listKnowledgeAreas(Long id) {
        return persistence.find(id).getKnowledgeAreasRequested();
    }

    @Override
    public KnowledgeAreaEntity getKnowledgeAreas(Long id, Long knowledgeAreaId) {
        List<KnowledgeAreaEntity> list = persistence.find(id).getKnowledgeAreasRequested();
        KnowledgeAreaEntity awardEntity = new KnowledgeAreaEntity();
        awardEntity.setId(knowledgeAreaId);
        int index = list.indexOf(awardEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    
    
    @Override
    public KnowledgeAreaEntity addKnowledgeAreas(Long id, Long KnowledgeAreaId){
        TranslationRequestEntity entity = persistence.find(id);
        KnowledgeAreaEntity knowledgeAreEntity = knowledgeAreaLogic.getKnowledgeArea(KnowledgeAreaId);
        //knowledgeAreEntity.setTraslationRequested(TraslationRequested);Author(entity);
        return knowledgeAreEntity;
    }

    @Override
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long id, List<KnowledgeAreaEntity> list) {
        TranslationRequestEntity entity = persistence.find(id);
        List<KnowledgeAreaEntity> knowledgeList = knowledgeAreaLogic.getKnowledgeAreas();
        for (KnowledgeAreaEntity knowledge : knowledgeList) {
            if (list.contains(knowledge)) {
                //knowledge.setAuthor(authorEntity);
            } else {
                //if (knowledge.getAuthor()!= null && award.getAuthor().equals(authorEntity)) {
                //    knowledge.setAuthor(null);
                //}
            }
        }
        entity.setKnowledgeAreasRequested(list);
        return entity.getKnowledgeAreasRequested();
    }

    
    @Override
    public void removeKnowledgeAreas(Long id, Long KnowledgeAreaId) {
        KnowledgeAreaEntity entity = knowledgeAreaLogic.getKnowledgeArea(KnowledgeAreaId);
        //entity.setAuthor(null);
    }   
}
