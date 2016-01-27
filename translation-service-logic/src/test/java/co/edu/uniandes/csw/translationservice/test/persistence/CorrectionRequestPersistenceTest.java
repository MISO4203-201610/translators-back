package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.persistence.CorrectionRequestPersistence;
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
public class CorrectionRequestPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CorrectionRequestEntity.class.getPackage())
                .addPackage(CorrectionRequestPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private CorrectionRequestPersistence correctionRequestPersistence;

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
        em.createQuery("delete from CorrectionRequestEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<CorrectionRequestEntity> data = new ArrayList<CorrectionRequestEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CorrectionRequestEntity entity = factory.manufacturePojo(CorrectionRequestEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCorrectionRequestTest() {
		PodamFactory factory = new PodamFactoryImpl();
        CorrectionRequestEntity newEntity = factory.manufacturePojo(CorrectionRequestEntity.class);
        CorrectionRequestEntity result = correctionRequestPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CorrectionRequestEntity entity = em.find(CorrectionRequestEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getStatus(), entity.getStatus());
    }

    /**
     * @generated
     */
    @Test
    public void getCorrectionRequestsTest() {
        List<CorrectionRequestEntity> list = correctionRequestPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CorrectionRequestEntity ent : list) {
            boolean found = false;
            for (CorrectionRequestEntity entity : data) {
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
    public void getCorrectionRequestTest() {
        CorrectionRequestEntity entity = data.get(0);
        CorrectionRequestEntity newEntity = correctionRequestPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getCreationDate(), newEntity.getCreationDate());
        Assert.assertEquals(entity.getDueDate(), newEntity.getDueDate());
        Assert.assertEquals(entity.getStatus(), newEntity.getStatus());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCorrectionRequestTest() {
        CorrectionRequestEntity entity = data.get(0);
        correctionRequestPersistence.delete(entity.getId());
        CorrectionRequestEntity deleted = em.find(CorrectionRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCorrectionRequestTest() {
        CorrectionRequestEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CorrectionRequestEntity newEntity = factory.manufacturePojo(CorrectionRequestEntity.class);

        newEntity.setId(entity.getId());

        correctionRequestPersistence.update(newEntity);

        CorrectionRequestEntity resp = em.find(CorrectionRequestEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getStatus(), resp.getStatus());
    }
}
