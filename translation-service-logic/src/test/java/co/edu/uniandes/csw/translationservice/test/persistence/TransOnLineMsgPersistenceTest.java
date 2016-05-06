package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.TransOnLineMsgEntity;
import co.edu.uniandes.csw.translationservice.persistence.TransOnLineMsgPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class TransOnLineMsgPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransOnLineMsgEntity.class.getPackage())
                .addPackage(TransOnLineMsgPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private TransOnLineMsgPersistence transOnLineMsgPersistence;

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
        em.createQuery("delete from TransOnLineMsgEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TransOnLineMsgEntity> data = new ArrayList<TransOnLineMsgEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TransOnLineMsgEntity entity = factory.manufacturePojo(TransOnLineMsgEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTrasnOnLineMsgTest() {
		PodamFactory factory = new PodamFactoryImpl();
        TransOnLineMsgEntity newEntity = factory.manufacturePojo(TransOnLineMsgEntity.class);
        TransOnLineMsgEntity result = transOnLineMsgPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TransOnLineMsgEntity entity = em.find(TransOnLineMsgEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getTransOnLineMsgsTest() {
        List<TransOnLineMsgEntity> list = transOnLineMsgPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TransOnLineMsgEntity ent : list) {
            boolean found = false;
            for (TransOnLineMsgEntity entity : data) {
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
    public void getTransOnLineMsgTest() {
        TransOnLineMsgEntity entity = data.get(0);
        TransOnLineMsgEntity newEntity = transOnLineMsgPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteChatMsgTest() {
        TransOnLineMsgEntity entity = data.get(0);
        transOnLineMsgPersistence.delete(entity.getId());
        TransOnLineMsgEntity deleted = em.find(TransOnLineMsgEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateChatMsgTest() {
        TransOnLineMsgEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransOnLineMsgEntity newEntity = factory.manufacturePojo(TransOnLineMsgEntity.class);

        newEntity.setId(entity.getId());

        transOnLineMsgPersistence.update(newEntity);

        TransOnLineMsgEntity resp = em.find(TransOnLineMsgEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}