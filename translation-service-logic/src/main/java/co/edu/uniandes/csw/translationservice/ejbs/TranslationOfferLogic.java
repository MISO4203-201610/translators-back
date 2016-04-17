package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITranslationOfferLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslationOfferPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *  @author Germán
 */
@Stateless
public class TranslationOfferLogic implements ITranslationOfferLogic {

    @Inject private TranslationOfferPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countTranslationOffers() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslationOfferEntity> getTranslationOffers() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslationOfferEntity> getTranslationOffers(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public TranslationOfferEntity getTranslationOffer(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public TranslationOfferEntity createTranslationOffer(TranslationOfferEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public TranslationOfferEntity updateTranslationOffer(TranslationOfferEntity entity) {
        TranslationOfferEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTranslationOffer(Long id) {
        persistence.delete(id);
    }
}
