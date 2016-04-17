package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.IKnowledgeAreaLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationOfferLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
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
    
    @Inject private ITranslationOfferLogic translationOfferLogic;

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
    public List<TranslationOfferEntity> listTranslationOffers(Long id) {
        return persistence.find(id).getTranslationOffers();
    }

    @Override
    public TranslationOfferEntity getTranslationOffers(Long id, Long TranslationOfferId) {
        List<TranslationOfferEntity> list = persistence.find(id).getTranslationOffers();
        TranslationOfferEntity awardEntity = new TranslationOfferEntity();
        awardEntity.setId(TranslationOfferId);
        int index = list.indexOf(awardEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    @Override
    public TranslationOfferEntity addTranslationOffers(Long id, Long TranslationOfferId) {
        TranslationOfferEntity translationOfferEntity = translationOfferLogic.getTranslationOffer(TranslationOfferId);

        TranslationRequestEntity entity = persistence.find(id);
        translationOfferEntity.setId(TranslationOfferId);
        entity.getTranslationOffers().add(translationOfferEntity);
        return getTranslationOffers(id, TranslationOfferId);
    }

    @Override
    public List<TranslationOfferEntity> replaceTranslationOffers(Long id, List<TranslationOfferEntity> list) {
        TranslationRequestEntity entity = persistence.find(id);
        List<TranslationOfferEntity> offerList = translationOfferLogic.getTranslationOffers();
        for (TranslationOfferEntity offer : offerList) {
            if (list.contains(offer)) {
                offer.setTranslationRequest(entity);
            } else {
                if (offer.getTranslationRequest()!= null && offer.getTranslationRequest().equals(entity)) {
                    offer.setTranslationRequest(null);
                }
            }
        }
        entity.setTranslationOffers(list);
        return entity.getTranslationOffers();
    }

    @Override
    public void removeTranslationOffers(Long id, Long TranslationOfferId) {
        TranslationRequestEntity entity = persistence.find(id);
        TranslationOfferEntity translationOfferEntity = translationOfferLogic.getTranslationOffer(TranslationOfferId);
        entity.getKnowledgeAreasRequested().remove(translationOfferEntity);
    }
}
