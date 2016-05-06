/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorOfertPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co   >
 */
@RunWith(Arquillian.class)
public class TranslatorOfertPersistenceTest {
      /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TranslatorOfertEntity.class.getPackage())
                .addPackage(TranslatorOfertPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private TranslatorOfertPersistence translatorOfertPersistence;

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
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from TranslatorOfertEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TranslatorOfertEntity> data = new ArrayList<TranslatorOfertEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TranslatorOfertEntity entity = factory.manufacturePojo(TranslatorOfertEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTranslatorOfertTest() {
		PodamFactory factory = new PodamFactoryImpl();
        TranslatorOfertEntity newEntity = factory.manufacturePojo(TranslatorOfertEntity.class);
        TranslatorOfertEntity result = translatorOfertPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TranslatorOfertEntity entity = em.find(TranslatorOfertEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getTranslatorOfertsTest() {
        List<TranslatorOfertEntity> list = translatorOfertPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TranslatorOfertEntity ent : list) {
            boolean found = false;
            for (TranslatorOfertEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void getTranslatorOfertTest() {
        TranslatorOfertEntity entity = data.get(0);
        TranslatorOfertEntity newEntity = translatorOfertPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPrice(), newEntity.getPrice());
        Assert.assertEquals(entity.getComment(), newEntity.getComment());
        Assert.assertEquals(entity.isAcceptedByCustomer(), newEntity.isAcceptedByCustomer());
        Assert.assertEquals(entity.getStatus(), newEntity.getStatus());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslatorOfertTest() {
        TranslatorOfertEntity entity = data.get(0);
        translatorOfertPersistence.delete(entity.getId());
        TranslatorOfertEntity deleted = em.find(TranslatorOfertEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslatorOfertTest() {
        TranslatorOfertEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TranslatorOfertEntity newEntity = factory.manufacturePojo(TranslatorOfertEntity.class);

        newEntity.setId(entity.getId());

        translatorOfertPersistence.update(newEntity);

        TranslatorOfertEntity resp = em.find(TranslatorOfertEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
