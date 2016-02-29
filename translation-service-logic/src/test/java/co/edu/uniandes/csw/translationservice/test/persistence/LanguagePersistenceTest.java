package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.persistence.LanguagePersistence;
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
public class LanguagePersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LanguageEntity.class.getPackage())
                .addPackage(LanguagePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private LanguagePersistence languagePersistence;

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
        em.createQuery("delete from LanguageEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<LanguageEntity> data = new ArrayList<LanguageEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LanguageEntity entity = factory.manufacturePojo(LanguageEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createLanguageTest() {
		PodamFactory factory = new PodamFactoryImpl();
        LanguageEntity newEntity = factory.manufacturePojo(LanguageEntity.class);
        LanguageEntity result = languagePersistence.create(newEntity);

        Assert.assertNotNull(result);

        LanguageEntity entity = em.find(LanguageEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getLanguagesTest() {
        List<LanguageEntity> list = languagePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LanguageEntity ent : list) {
            boolean found = false;
            for (LanguageEntity entity : data) {
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
    public void getLanguageTest() {
        LanguageEntity entity = data.get(0);
        LanguageEntity newEntity = languagePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteLanguageTest() {
        LanguageEntity entity = data.get(0);
        languagePersistence.delete(entity.getId());
        LanguageEntity deleted = em.find(LanguageEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateLanguageTest() {
        LanguageEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LanguageEntity newEntity = factory.manufacturePojo(LanguageEntity.class);

        newEntity.setId(entity.getId());

        languagePersistence.update(newEntity);

        LanguageEntity resp = em.find(LanguageEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
