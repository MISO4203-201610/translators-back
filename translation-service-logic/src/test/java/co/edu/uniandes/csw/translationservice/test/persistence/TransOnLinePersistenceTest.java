package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.TransOnLineEntity;
import co.edu.uniandes.csw.translationservice.persistence.TransOnLinePersistence;
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
public class TransOnLinePersistenceTest {

    /**
     * @return 
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransOnLineEntity.class.getPackage())
                .addPackage(TransOnLinePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private TransOnLinePersistence transOnLinePersistence;

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
        em.createQuery("delete from TransOnLineEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TransOnLineEntity> data = new ArrayList<TransOnLineEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TransOnLineEntity entity = factory.manufacturePojo(TransOnLineEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTransOnLineTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TransOnLineEntity newEntity = factory.manufacturePojo(TransOnLineEntity.class);
        TransOnLineEntity result = transOnLinePersistence.create(newEntity);

        Assert.assertNotNull(result);

        TransOnLineEntity entity = em.find(TransOnLineEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCreationDate(), entity.getCreationDate());
    }

    /**
     * @generated
     */
    @Test
    public void getTransOnLinesTest() {
        List<TransOnLineEntity> list = transOnLinePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TransOnLineEntity ent : list) {
            boolean found = false;
            for (TransOnLineEntity entity : data) {
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
    public void getTransOnLineTest() {
        TransOnLineEntity entity = data.get(0);
        TransOnLineEntity newEntity = transOnLinePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getCreationDate(), newEntity.getCreationDate());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTransOnLineTest() {
        TransOnLineEntity entity = data.get(0);
        transOnLinePersistence.delete(entity.getId());
        TransOnLineEntity deleted = em.find(TransOnLineEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTransOnLineTest() {
        TransOnLineEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransOnLineEntity newEntity = factory.manufacturePojo(TransOnLineEntity.class);

        newEntity.setId(entity.getId());

        transOnLinePersistence.update(newEntity);

        TransOnLineEntity resp = em.find(TransOnLineEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getCreationDate(), resp.getCreationDate());
    }
}
