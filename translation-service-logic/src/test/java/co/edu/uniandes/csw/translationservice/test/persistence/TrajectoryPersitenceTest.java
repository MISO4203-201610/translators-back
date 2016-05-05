/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.TrajectoryEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.persistence.TrajectoryPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class TrajectoryPersitenceTest {
    
    /**
     * @generated
     */
    private List<TranslatorEntity> translatorData = new ArrayList<>();
    
    /**
     * @generated
     */
    private List<TrajectoryEntity> data = new ArrayList<TrajectoryEntity>();
    
    /**
     * Default class logger
     */
    private static final Logger logger = Logger.getLogger(TrajectoryPersitenceTest.class.getName() );
    
    /**
     * @generated
     * @return new deployment file
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrajectoryEntity.class.getPackage())
                .addPackage(TrajectoryPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private TrajectoryPersistence trajectoryPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * @generated
     */
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | NotSupportedException | SystemException e) 
        {
            logger.log( Level.SEVERE, e.toString(), e);
            try 
            {
                utx.rollback();
            } 
            catch (IllegalStateException | SecurityException | SystemException e1) 
            {
                logger.log( Level.SEVERE, e.toString(), e1);
            } 
        }
        
    }

    /**
     * @generated
     */
    private void clearData() 
    {
        em.createQuery("delete from TrajectoryEntity").executeUpdate();
        em.createQuery("delete from TranslatorEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) 
        {
            TranslatorEntity translators = factory.manufacturePojo(TranslatorEntity.class);
            em.persist(translators);
            translatorData.add(translators);
        }

        for (int i = 0; i < 3; i++) 
        {
            TrajectoryEntity entity = factory.manufacturePojo(TrajectoryEntity.class);
            entity.setTranslator(translatorData.get(i));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTrajectoryTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        TrajectoryEntity newEntity = factory.manufacturePojo(TrajectoryEntity.class);
        TrajectoryEntity result = trajectoryPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TrajectoryEntity entity = em.find(TrajectoryEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getProjectName(), entity.getProjectName());
    }

    /**
     * @generated
     */
    @Test
    public void getTrajectoryTest() 
    {
        TrajectoryEntity entity = data.get(0);
        TrajectoryEntity newEntity = trajectoryPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getProjectName(), newEntity.getProjectName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTrajectoryTest() 
    {
        TrajectoryEntity entity = data.get(0);
        trajectoryPersistence.delete(entity.getId());
        TrajectoryEntity deleted = em.find(TrajectoryEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTrajectoryTest() 
    {
        TrajectoryEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TrajectoryEntity newEntity = factory.manufacturePojo(TrajectoryEntity.class);

        newEntity.setId(entity.getId());

        trajectoryPersistence.update(newEntity);

        TrajectoryEntity resp = em.find(TrajectoryEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getProjectName(), resp.getProjectName());
    }
    
}
