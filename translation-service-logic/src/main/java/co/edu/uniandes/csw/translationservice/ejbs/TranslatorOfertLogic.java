package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITranslatorOfertLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorOfertPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *  @author Germán
 */
@Stateless
public class TranslatorOfertLogic implements ITranslatorOfertLogic {

    @Inject private TranslatorOfertPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countTranslatorOferts() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorOfertEntity> getTranslatorOferts() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorOfertEntity> getTranslatorOferts(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity getTranslatorOfert(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity createTranslatorOfert(TranslatorOfertEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity updateTranslatorOfert(TranslatorOfertEntity entity) {
        TranslatorOfertEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTranslatorOfert(Long id) {
        persistence.delete(id);
    }
}
