package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
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
        TranslationRequestEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTranslationRequest(Long id) {
        persistence.delete(id);
    }
}
