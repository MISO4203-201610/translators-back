package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorPersistence;
import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
import co.edu.uniandes.csw.translationservice.api.ITranslatorOfertLogic;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class TranslatorLogic implements ITranslatorLogic {

    @Inject private TranslatorPersistence persistence;
    
    @Inject private ITranslatorOfertLogic translatorOfertLogic;
    
    

    /**
     * @generated
     */
    @Override
    public int countTranslators() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorEntity> getTranslators() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorEntity> getTranslators(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public TranslatorEntity getTranslator(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public TranslatorEntity createTranslator(TranslatorEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public TranslatorEntity updateTranslator(TranslatorEntity entity) {
        TranslatorEntity newEntity = entity;
        TranslatorEntity oldEntity = persistence.find(entity.getId());
        newEntity.setTranslatorOferts(oldEntity.getTranslatorOferts());
        newEntity.setLanguages(oldEntity.getLanguages());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTranslator(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorOfertEntity> listTranslatorOferts(Long translatorId) {
        return persistence.find(translatorId).getTranslatorOferts();
    }

    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity getTranslatorOferts(Long translatorId, Long translatorOfertsId) {
        List<TranslatorOfertEntity> list = persistence.find(translatorId).getTranslatorOferts();
        TranslatorOfertEntity translatorOfertsEntity = new TranslatorOfertEntity();
        translatorOfertsEntity.setId(translatorOfertsId);
        int index = list.indexOf(translatorOfertsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity addTranslatorOferts(Long translatorId, Long translatorOfertsId) {
        TranslatorEntity translatorEntity = persistence.find(translatorId);
        TranslatorOfertEntity translatorOfertsEntity = translatorOfertLogic.getTranslatorOfert(translatorOfertsId);
        translatorOfertsEntity.setTranslator(translatorEntity);
        return translatorOfertsEntity;
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorOfertEntity> replaceTranslatorOferts(Long translatorId, List<TranslatorOfertEntity> list) {
        TranslatorEntity translatorEntity = persistence.find(translatorId);
        List<TranslatorOfertEntity> translatorOfertList = translatorOfertLogic.getTranslatorOferts();
        for (TranslatorOfertEntity translatorOfert : translatorOfertList) {
            if (list.contains(translatorOfert)) {
                translatorOfert.setTranslator(translatorEntity);
            } else {
                if (translatorOfert.getTranslator() != null && translatorOfert.getTranslator().equals(translatorEntity)) {
                    translatorOfert.setTranslator(null);
                }
            }
        }
        translatorEntity.setTranslatorOferts(list);
        return translatorEntity.getTranslatorOferts();
    }

    /**
     * @generated
     */
    @Override
    public void removeTranslatorOferts(Long translatorId, Long translatorOfertsId) {
        TranslatorOfertEntity entity = translatorOfertLogic.getTranslatorOfert(translatorOfertsId);
        entity.setTranslator(null);
    }
    
    /**
     * @generated
     */
    @Override
    public List<LanguageEntity> listLanguages(Long translatorId) {
        return persistence.find(translatorId).getLanguages();
    }

    /**
     * @generated
     */
    @Override
    public LanguageEntity getLanguages(Long translatorId, Long languagesId) {
        List<LanguageEntity> list = persistence.find(translatorId).getLanguages();
        LanguageEntity languagesEntity = new LanguageEntity();
        languagesEntity.setId(languagesId);
        int index = list.indexOf(languagesEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public LanguageEntity addLanguages(Long translatorId, Long languagesId) {
        TranslatorEntity translatorEntity = persistence.find(translatorId);
        LanguageEntity languagesEntity = new LanguageEntity();
        languagesEntity.setId(languagesId);
        translatorEntity.getLanguages().add(languagesEntity);
        return getLanguages(translatorId, languagesId);
    }

    /**
     * @generated
     */
    @Override
    public List<LanguageEntity> replaceLanguages(Long translatorId, List<LanguageEntity> list) {
        TranslatorEntity translatorEntity = persistence.find(translatorId);
        translatorEntity.setLanguages(list);
        return translatorEntity.getLanguages();
    }

    /**
     * @generated
     */
    @Override
    public void removeLanguages(Long translatorId, Long languagesId) {
        TranslatorEntity entity = persistence.find(translatorId);
        LanguageEntity languagesEntity = new LanguageEntity();
        languagesEntity.setId(languagesId);
        entity.getLanguages().remove(languagesEntity);
    }
    
    /**
     * @generated
     */
    @Override
    public List<KnowledgeAreaEntity> listKnowledgeAreas(Long translatorId) {
        return persistence.find(translatorId).getKnowledgeAreas();
    }

    /**
     * @generated
     */
    @Override
    public KnowledgeAreaEntity getKnowledgeAreas(Long translatorId, Long knowledgeAreasId) {
        List<KnowledgeAreaEntity> list = persistence.find(translatorId).getKnowledgeAreas();
        KnowledgeAreaEntity knowledgeAreasEntity = new KnowledgeAreaEntity();
        knowledgeAreasEntity.setId(knowledgeAreasId);
        int index = list.indexOf(knowledgeAreasEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    @Override
    public KnowledgeAreaEntity addKnowledgeAreas(Long translatorId, Long knowledgeAreasId) {
        TranslatorEntity translatorEntity = persistence.find(translatorId);
        KnowledgeAreaEntity knowledgeAreasEntity = new KnowledgeAreaEntity();
        knowledgeAreasEntity.setId(knowledgeAreasId);
        translatorEntity.getKnowledgeAreas().add(knowledgeAreasEntity);
        return getKnowledgeAreas(translatorId, knowledgeAreasId);
    }

    /**
     * @generated
     */
    @Override
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long translatorId, List<KnowledgeAreaEntity> list) {
        TranslatorEntity translatorEntity = persistence.find(translatorId);
        translatorEntity.setKnowledgeAreas(list);
        return translatorEntity.getKnowledgeAreas();
    }

    /**
     * @generated
     */
    @Override
    public void removeKnowledgeAreas(Long translatorId, Long knowledgeAreasId) {
        TranslatorEntity entity = persistence.find(translatorId);
        KnowledgeAreaEntity knowledgeAreasEntity = new KnowledgeAreaEntity();
        knowledgeAreasEntity.setId(knowledgeAreasId);
        entity.getKnowledgeAreas().remove(knowledgeAreasEntity);
    }
    
    @Override
    public List<TranslatorOfertEntity> getAcceptedOferts(Long translatorId)
    {
        return translatorOfertLogic.getAcceptedOferts(translatorId);
    }

}
