package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslationRequestPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
public class TranslationRequestPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TranslationRequestEntity.class.getPackage())
                .addPackage(TranslationRequestPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private TranslationRequestPersistence translationRequestPersistence;

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
        em.createQuery("delete from TranslationRequestEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TranslationRequestEntity> data = new ArrayList<TranslationRequestEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TranslationRequestEntity entity = factory.manufacturePojo(TranslationRequestEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTranslationRequestTest() {
		PodamFactory factory = new PodamFactoryImpl();
        TranslationRequestEntity newEntity = factory.manufacturePojo(TranslationRequestEntity.class);
        TranslationRequestEntity result = translationRequestPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TranslationRequestEntity entity = em.find(TranslationRequestEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getTranslationRequestsTest() {
        List<TranslationRequestEntity> list = translationRequestPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TranslationRequestEntity ent : list) {
            boolean found = false;
            for (TranslationRequestEntity entity : data) {
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
    public void getTranslationRequestTest() {
        TranslationRequestEntity entity = data.get(0);
        TranslationRequestEntity newEntity = translationRequestPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getCreationDate(), newEntity.getCreationDate());
        Assert.assertEquals(entity.getDueDate(), newEntity.getDueDate());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslationRequestTest() {
        TranslationRequestEntity entity = data.get(0);
        translationRequestPersistence.delete(entity.getId());
        TranslationRequestEntity deleted = em.find(TranslationRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslationRequestTest() {
        TranslationRequestEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TranslationRequestEntity newEntity = factory.manufacturePojo(TranslationRequestEntity.class);

        newEntity.setId(entity.getId());

        translationRequestPersistence.update(newEntity);

        TranslationRequestEntity resp = em.find(TranslationRequestEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
