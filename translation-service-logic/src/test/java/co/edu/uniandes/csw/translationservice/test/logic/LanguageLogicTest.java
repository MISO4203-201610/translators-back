package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.LanguageLogic;
import co.edu.uniandes.csw.translationservice.api.ILanguageLogic;
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
public class LanguageLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ILanguageLogic languageLogic;

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
    private List<LanguageEntity> data = new ArrayList<LanguageEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LanguageEntity.class.getPackage())
                .addPackage(LanguageLogic.class.getPackage())
                .addPackage(ILanguageLogic.class.getPackage())
                .addPackage(LanguagePersistence.class.getPackage())
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
        em.createQuery("delete from LanguageEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
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
        LanguageEntity entity = factory.manufacturePojo(LanguageEntity.class);
        LanguageEntity result = languageLogic.createLanguage(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getLanguagesTest() {
        List<LanguageEntity> list = languageLogic.getLanguages();
        Assert.assertEquals(data.size(), list.size());
        for (LanguageEntity entity : list) {
            boolean found = false;
            for (LanguageEntity storedEntity : data) {
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
    public void getLanguageTest() {
        LanguageEntity entity = data.get(0);
        LanguageEntity resultEntity = languageLogic.getLanguage(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteLanguageTest() {
        LanguageEntity entity = data.get(1);
        languageLogic.deleteLanguage(entity.getId());
        LanguageEntity deleted = em.find(LanguageEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateLanguageTest() {
        LanguageEntity entity = data.get(0);
        LanguageEntity pojoEntity = factory.manufacturePojo(LanguageEntity.class);

        pojoEntity.setId(entity.getId());

        languageLogic.updateLanguage(pojoEntity);

        LanguageEntity resp = em.find(LanguageEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
