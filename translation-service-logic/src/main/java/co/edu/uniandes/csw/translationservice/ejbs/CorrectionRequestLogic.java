package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.api.IKnowledgeAreaLogic;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.persistence.CorrectionRequestPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class CorrectionRequestLogic implements ICorrectionRequestLogic {

    @Inject private CorrectionRequestPersistence persistence;
    
    @Inject private IKnowledgeAreaLogic knowledgeAreaLogic;

    /**
     * @generated
     */
    @Override
    public int countCorrectionRequests() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<CorrectionRequestEntity> getCorrectionRequests() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<CorrectionRequestEntity> getCorrectionRequests(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public CorrectionRequestEntity getCorrectionRequest(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public CorrectionRequestEntity createCorrectionRequest(CorrectionRequestEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public CorrectionRequestEntity updateCorrectionRequest(CorrectionRequestEntity entity) {
        CorrectionRequestEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteCorrectionRequest(Long id) {
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
        //TranslationRequestEntity entity = persistence.find(id);
        KnowledgeAreaEntity knowledgeAreasEntity = knowledgeAreaLogic.getKnowledgeArea(KnowledgeAreaId);
        //knowledgeAreEntity.setTraslationRequest(entity);
        //entity.get(knowledgeAreasRequested);
        //return knowledgeAreEntity;
        
        CorrectionRequestEntity entity = persistence.find(id);
        knowledgeAreasEntity.setId(KnowledgeAreaId);
        entity.getKnowledgeAreasRequested().add(knowledgeAreasEntity);
        return getKnowledgeAreas(id, KnowledgeAreaId);
    }

    @Override
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long id, List<KnowledgeAreaEntity> list) {
        CorrectionRequestEntity entity = persistence.find(id);
        List<KnowledgeAreaEntity> knowledgeList = knowledgeAreaLogic.getKnowledgeAreas();
        for (KnowledgeAreaEntity knowledge : knowledgeList) {
            if (list.contains(knowledge)) {
                knowledge.setCorrectionRequest(entity);
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
    public void removeKnowledgeAreas(Long id, Long knowledgeAreaId) {
        
        CorrectionRequestEntity entity = persistence.find(id);
        KnowledgeAreaEntity knowledgeAreasEntity = knowledgeAreaLogic.getKnowledgeArea(knowledgeAreaId);
        entity.getKnowledgeAreasRequested().remove(knowledgeAreasEntity);
    }   
}
