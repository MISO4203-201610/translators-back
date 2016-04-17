package co.edu.uniandes.csw.translationservice.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;

/**
 *  @author Germán
 */
@Stateless
public class TranslationOfferPersistence extends CrudPersistence<TranslationOfferEntity> {

    @PersistenceContext(unitName="TranslationServicePU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<TranslationOfferEntity> getEntityClass() {
        return TranslationOfferEntity.class;
    }
}
