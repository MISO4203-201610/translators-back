package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
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
        CorrectionRequestEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteCorrectionRequest(Long id) {
        persistence.delete(id);
    }
}
