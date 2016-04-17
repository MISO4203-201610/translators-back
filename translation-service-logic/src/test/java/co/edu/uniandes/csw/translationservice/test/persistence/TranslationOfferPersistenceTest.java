package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.entities.EducationEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorPersistence;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslationOfferPersistence;
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
public class TranslationOfferPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TranslatorEntity.class.getPackage())
                .addPackage(TranslatorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private TranslationOfferPersistence translationOfferPersistence;

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
        em.createQuery("delete from TranslationOfferEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TranslationOfferEntity> data = new ArrayList<TranslationOfferEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TranslationOfferEntity entity = factory.manufacturePojo(TranslationOfferEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTranslatorTest() {
		PodamFactory factory = new PodamFactoryImpl();
        TranslationOfferEntity newEntity = factory.manufacturePojo(TranslationOfferEntity.class);
        TranslationOfferEntity result = translationOfferPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TranslationOfferEntity entity = em.find(TranslationOfferEntity.class, result.getId());

        Assert.assertEquals(newEntity.getComment(), entity.getComment());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
    }

    /**
     * @generated
     */
    @Test
    public void getTranslatorsTest() {
        List<TranslationOfferEntity> list = translationOfferPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TranslationOfferEntity ent : list) {
            boolean found = false;
            for (TranslationOfferEntity entity : data) {
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
    public void getTranslatorTest() {
        TranslationOfferEntity entity = data.get(0);
        TranslationOfferEntity newEntity = translationOfferPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPrice(), newEntity.getPrice());
        Assert.assertEquals(entity.getComment(), newEntity.getComment());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslatorTest() {
        TranslationOfferEntity entity = data.get(0);
        translationOfferPersistence.delete(entity.getId());
        TranslationOfferEntity deleted = em.find(TranslationOfferEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslatorTest() {
        TranslationOfferEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TranslationOfferEntity newEntity = factory.manufacturePojo(TranslationOfferEntity.class);

        newEntity.setId(entity.getId());

        translationOfferPersistence.update(newEntity);

        TranslationOfferEntity resp = em.find(TranslationOfferEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getComment(), resp.getComment());
        Assert.assertEquals(newEntity.getPrice(), resp.getPrice());
    }
}
