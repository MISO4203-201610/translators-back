package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ILanguageLogic;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.persistence.LanguagePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class LanguageLogic implements ILanguageLogic {

    @Inject private LanguagePersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countLanguages() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<LanguageEntity> getLanguages() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<LanguageEntity> getLanguages(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public LanguageEntity getLanguage(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public LanguageEntity createLanguage(LanguageEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public LanguageEntity updateLanguage(LanguageEntity entity) {
        LanguageEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteLanguage(Long id) {
        persistence.delete(id);
    }
}
