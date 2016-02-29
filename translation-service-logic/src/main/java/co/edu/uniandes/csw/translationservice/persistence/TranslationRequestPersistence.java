package co.edu.uniandes.csw.translationservice.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;

/**
 * @generated
 */
@Stateless
public class TranslationRequestPersistence extends CrudPersistence<TranslationRequestEntity> {

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
    protected Class<TranslationRequestEntity> getEntityClass() {
        return TranslationRequestEntity.class;
    }
}
