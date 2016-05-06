package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITransOnLineLogic;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineEntity;
import co.edu.uniandes.csw.translationservice.persistence.TransOnLinePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class TransOnLineLogic implements ITransOnLineLogic {

    @Inject private TransOnLinePersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countTransOnLines() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<TransOnLineEntity> getTransOnLines() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<TransOnLineEntity> getTransOnLines(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public TransOnLineEntity getTransOnLine(String description) {
        List<TransOnLineEntity> listChat =null;
        TransOnLineEntity res = null;
        listChat=persistence.findByName(description);
        if(listChat.size()>0)
            res=listChat.get(0);
        return res;
    }

    /**
     * @generated
     */
    @Override
    public TransOnLineEntity createTransOnLine(TransOnLineEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public TransOnLineEntity updateTransOnLine(TransOnLineEntity entity) {
        TransOnLineEntity newEntity = entity;
        TransOnLineEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTransOnLine(Long id) {
        persistence.delete(id);
    }
}
