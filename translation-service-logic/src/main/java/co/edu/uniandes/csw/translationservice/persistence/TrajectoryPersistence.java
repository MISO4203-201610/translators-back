/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.persistence;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.translationservice.entities.TrajectoryEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class TrajectoryPersistence extends CrudPersistence<TrajectoryEntity> {
     
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
    protected Class<TrajectoryEntity> getEntityClass() {
        return TrajectoryEntity.class;
    }
    
    public List<TrajectoryEntity> getByTranslator(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",  id );
        return executeListNamedQuery("Trajectory.getByTranslator", params);
    }
    
}
