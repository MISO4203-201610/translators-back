package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.api.ITransOnLineMsgLogic;
import co.edu.uniandes.csw.translationservice.ejbs.TransOnLineMsgLogic;
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
public class TransOnLineMsgLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITransOnLineMsgLogic transOnLineMsgLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<TransOnLineMsgEntity> data = new ArrayList<TransOnLineMsgEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransOnLineMsgEntity.class.getPackage())
                .addPackage(TransOnLineMsgLogic.class.getPackage())
                .addPackage(ITransOnLineMsgLogic.class.getPackage())
                .addPackage(TransOnLineMsgPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
    private void insertData() {
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
    public void createTransOnLineMsgTest() {
        TransOnLineMsgEntity entity = factory.manufacturePojo(TransOnLineMsgEntity.class);
        TransOnLineMsgEntity result = transOnLineMsgLogic.createTransOnLineMsg(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getTransOnLineMsgsTest() {
        List<TransOnLineMsgEntity> list = transOnLineMsgLogic.getTransOnLineMsgs();
        Assert.assertEquals(data.size(), list.size());
        for (TransOnLineMsgEntity entity : list) {
            boolean found = false;
            for (TransOnLineMsgEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        TransOnLineMsgEntity resultEntity = transOnLineMsgLogic.getTransOnLineMsg(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTransOnLineMsgTest() {
        TransOnLineMsgEntity entity = data.get(1);
        transOnLineMsgLogic.deleteTransOnLineMsg(entity.getId());
        TransOnLineMsgEntity deleted = em.find(TransOnLineMsgEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTransOnLineMsgTest() {
        TransOnLineMsgEntity entity = data.get(0);
        TransOnLineMsgEntity pojoEntity = factory.manufacturePojo(TransOnLineMsgEntity.class);

        pojoEntity.setId(entity.getId());

        transOnLineMsgLogic.updateTransOnLineMsg(pojoEntity);

        TransOnLineMsgEntity resp = em.find(TransOnLineMsgEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
