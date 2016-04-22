package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.IKnowledgeAreaLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorOfertLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
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
    
    @Inject private ITranslatorOfertLogic translatorOfertLogic;

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
    public CustomerEntity getCustomer(Long id) {
        CustomerEntity customer = persistence.find(id).getCustomer();
        return customer;
    }
   
    @Override
    public KnowledgeAreaEntity addKnowledgeAreas(Long id, Long KnowledgeAreaId){

        KnowledgeAreaEntity knowledgeAreasEntity = knowledgeAreaLogic.getKnowledgeArea(KnowledgeAreaId);

        TranslationRequestEntity entity = persistence.find(id);
        knowledgeAreasEntity.setId(KnowledgeAreaId);
        entity.getKnowledgeAreasRequested().add(knowledgeAreasEntity);
        return getKnowledgeAreas(id, KnowledgeAreaId);
    }

    @Override
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long id, List<KnowledgeAreaEntity> list) {
        TranslationRequestEntity entity = persistence.find(id);
        List<KnowledgeAreaEntity> knowledgeList = knowledgeAreaLogic.getKnowledgeAreas();
        for (KnowledgeAreaEntity knowledge : knowledgeList) {
            if (list.contains(knowledge)) {
                knowledge.setTraslationRequest(entity);
            } else {
                if (knowledge.getTraslationRequest()!= null && knowledge.getTraslationRequest().equals(entity)) {
                    knowledge.setTraslationRequest(null);
                }
            }
        }
        entity.setKnowledgeAreasRequested(list);
        return entity.getKnowledgeAreasRequested();
    }

    
    @Override
    public void removeKnowledgeAreas(Long id, Long KnowledgeAreaId) {
        
        TranslationRequestEntity entity = persistence.find(id);
        KnowledgeAreaEntity knowledgeAreasEntity = knowledgeAreaLogic.getKnowledgeArea(KnowledgeAreaId);
        entity.getKnowledgeAreasRequested().remove(knowledgeAreasEntity);
    }   
    
    @Override
    public List<TranslatorOfertEntity> listTranslatorOferts(Long id) {
        return persistence.find(id).getTranslatorOferts();
    }

    @Override
    public TranslatorOfertEntity getTranslatorOferts(Long id, Long translatorOfertId) {
        List<TranslatorOfertEntity> list = persistence.find(id).getTranslatorOferts();
        TranslatorOfertEntity awardEntity = new TranslatorOfertEntity();
        awardEntity.setId(translatorOfertId);
        int index = list.indexOf(awardEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
    
    @Override
    public TranslatorOfertEntity addTranslatorOferts(Long id, Long translatorOfertId){

        TranslatorOfertEntity translatorOfertsEntity = translatorOfertLogic.getTranslatorOfert(translatorOfertId);

        TranslationRequestEntity entity = persistence.find(id);
        translatorOfertsEntity.setId(translatorOfertId);
        entity.getTranslatorOferts().add(translatorOfertsEntity);
        return getTranslatorOferts(id, translatorOfertId);
    }

    @Override
    public List<TranslatorOfertEntity> replaceTranslatorOferts(Long id, List<TranslatorOfertEntity> list) {
        TranslationRequestEntity entity = persistence.find(id);
        List<TranslatorOfertEntity> translatorOfertList = translatorOfertLogic.getTranslatorOferts();
        for (TranslatorOfertEntity translatorOfert : translatorOfertList) {
            if (list.contains(translatorOfert)) {
                translatorOfert.setTranslationRequest(entity);
            } else {
                if (translatorOfert.getTranslationRequest()!= null && translatorOfert.getTranslationRequest().equals(entity)) {
                    translatorOfert.setTranslationRequest(null);
                }
            }
        }
        entity.setTranslatorOferts(list);
        return entity.getTranslatorOferts();
    }

    
    @Override
    public void removeTranslatorOferts(Long id, Long translatorOfertId) {
        
        TranslationRequestEntity entity = persistence.find(id);
        TranslatorOfertEntity translatorOfertsEntity = translatorOfertLogic.getTranslatorOfert(translatorOfertId);
        entity.getTranslatorOferts().remove(translatorOfertsEntity);
    }   
}
