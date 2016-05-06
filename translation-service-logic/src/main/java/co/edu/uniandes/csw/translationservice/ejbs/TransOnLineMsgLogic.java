package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITransOnLineMsgLogic;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineMsgEntity;
import co.edu.uniandes.csw.translationservice.persistence.TransOnLineMsgPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class TransOnLineMsgLogic implements ITransOnLineMsgLogic {

    @Inject private TransOnLineMsgPersistence persistence;
            
    /**
     * @generated
     */
    @Override
    public int countTransOnLineMsgs() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<TransOnLineMsgEntity> getTransOnLineMsgs() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<TransOnLineMsgEntity> getTransOnLineMsgs(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public TransOnLineMsgEntity getTransOnLineMsg(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public TransOnLineMsgEntity createTransOnLineMsg(TransOnLineMsgEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public TransOnLineMsgEntity updateTransOnLineMsg(TransOnLineMsgEntity entity) {
        TransOnLineMsgEntity newEntity = entity;
        TransOnLineMsgEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTransOnLineMsg(Long id) {
        persistence.delete(id);
    }
            
}
