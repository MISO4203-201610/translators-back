package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.api.ITransOnLineLogic;
import co.edu.uniandes.csw.translationservice.ejbs.TransOnLineLogic;
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
public class TransOnLineLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITransOnLineLogic transOnLineLogic;

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
    private List<TransOnLineEntity> data = new ArrayList<TransOnLineEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransOnLineEntity.class.getPackage())
                .addPackage(TransOnLineLogic.class.getPackage())
                .addPackage(ITransOnLineLogic.class.getPackage())
                .addPackage(TransOnLinePersistence.class.getPackage())
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
        em.createQuery("delete from ChatNameEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
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
        TransOnLineEntity entity = factory.manufacturePojo(TransOnLineEntity.class);
        TransOnLineEntity result = transOnLineLogic.createTransOnLine(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getTransOnLineTest() {
        List<TransOnLineEntity> list = transOnLineLogic.getTransOnLines();
        Assert.assertEquals(data.size(), list.size());
        for (TransOnLineEntity entity : list) {
            boolean found = false;
            for (TransOnLineEntity storedEntity : data) {
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
    public void getTransOnLinesTest() {
        TransOnLineEntity entity = data.get(0);
        TransOnLineEntity resultEntity = transOnLineLogic.getTransOnLine(entity.getName());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTransOnLineTest() {
        TransOnLineEntity entity = data.get(1);
        transOnLineLogic.deleteTransOnLine(entity.getId());
        TransOnLineEntity deleted = em.find(TransOnLineEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTransOnLineTest() {
        TransOnLineEntity entity = data.get(0);
        TransOnLineEntity pojoEntity = factory.manufacturePojo(TransOnLineEntity.class);

        pojoEntity.setId(entity.getId());

        transOnLineLogic.updateTransOnLine(pojoEntity);

        TransOnLineEntity resp = em.find(TransOnLineEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
