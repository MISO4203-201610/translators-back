/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineMsgEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juan camilo cerquera <jc.cerquera10@uniandes.edu.co>
 */
@Stateless
public class TransOnLineMsgPersistence extends CrudPersistence<TransOnLineMsgEntity> {
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
    protected Class<TransOnLineMsgEntity> getEntityClass() {
        return TransOnLineMsgEntity.class;
    }
    
    public List<TransOnLineMsgEntity> getListByChatId( Long chatNameId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatNameId",chatNameId);
        return executeListNamedQuery("ChatMsg.getByChatNameId", params);
    }
    
}

