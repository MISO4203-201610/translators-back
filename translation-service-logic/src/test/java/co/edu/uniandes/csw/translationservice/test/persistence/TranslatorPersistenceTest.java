package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.entities.EducationEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorPersistence;
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
public class TranslatorPersistenceTest {

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
    private TranslatorPersistence translatorPersistence;

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
        em.createQuery("delete from EducationEntity").executeUpdate();
        em.createQuery("delete from TranslatorEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TranslatorEntity> data = new ArrayList<TranslatorEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TranslatorEntity entity = factory.manufacturePojo(TranslatorEntity.class);
            for (EducationEntity item : entity.getEducation()) {
                item.setTranslator(entity);
            }
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
        TranslatorEntity newEntity = factory.manufacturePojo(TranslatorEntity.class);
        TranslatorEntity result = translatorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TranslatorEntity entity = em.find(TranslatorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getTranslatorsTest() {
        List<TranslatorEntity> list = translatorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TranslatorEntity ent : list) {
            boolean found = false;
            for (TranslatorEntity entity : data) {
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
        TranslatorEntity entity = data.get(0);
        TranslatorEntity newEntity = translatorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslatorTest() {
        TranslatorEntity entity = data.get(0);
        translatorPersistence.delete(entity.getId());
        TranslatorEntity deleted = em.find(TranslatorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslatorTest() {
        TranslatorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TranslatorEntity newEntity = factory.manufacturePojo(TranslatorEntity.class);

        newEntity.setId(entity.getId());

        translatorPersistence.update(newEntity);

        TranslatorEntity resp = em.find(TranslatorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
