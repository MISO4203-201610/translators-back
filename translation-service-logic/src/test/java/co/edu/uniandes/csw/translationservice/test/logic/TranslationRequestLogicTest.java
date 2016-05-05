package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.TranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslationRequestPersistence;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.entities.StatusEntity;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
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
public class TranslationRequestLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITranslationRequestLogic translationRequestLogic;

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
    private List<TranslationRequestEntity> data = new ArrayList<TranslationRequestEntity>();

    /**
     * @generated
     */
    private List<LanguageEntity> originalLanguageData = new ArrayList<>();

    /**
     * @generated
     */
    private List<StatusEntity> statusData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CustomerEntity> customerData = new ArrayList<>();

    /**
     * @generated
     */
    private List<LanguageEntity> targetLanguageData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TranslationRequestEntity.class.getPackage())
                .addPackage(TranslationRequestLogic.class.getPackage())
                .addPackage(ITranslationRequestLogic.class.getPackage())
                .addPackage(TranslationRequestPersistence.class.getPackage())
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
        em.createQuery("delete from TranslationRequestEntity").executeUpdate();
        em.createQuery("delete from LanguageEntity").executeUpdate();
        em.createQuery("delete from StatusEntity").executeUpdate();
        em.createQuery("delete from CustomerEntity").executeUpdate();
        em.createQuery("delete from LanguageEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LanguageEntity originalLanguage = factory.manufacturePojo(LanguageEntity.class);
            em.persist(originalLanguage);
            originalLanguageData.add(originalLanguage);
        }

        for (int i = 0; i < 3; i++) {
            StatusEntity status = factory.manufacturePojo(StatusEntity.class);
            em.persist(status);
            statusData.add(status);
        }

        for (int i = 0; i < 3; i++) {
            CustomerEntity customer = factory.manufacturePojo(CustomerEntity.class);
            em.persist(customer);
            customerData.add(customer);
        }

        for (int i = 0; i < 3; i++) {
            LanguageEntity targetLanguage = factory.manufacturePojo(LanguageEntity.class);
            em.persist(targetLanguage);
            targetLanguageData.add(targetLanguage);
        }

        for (int i = 0; i < 3; i++) {
            TranslationRequestEntity entity = factory.manufacturePojo(TranslationRequestEntity.class);

            entity.setOriginalLanguage(originalLanguageData.get(0));

            entity.setStatus(statusData.get(0));

            entity.setCustomer(customerData.get(0));

            entity.setTargetLanguage(targetLanguageData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTranslationRequestTest() {
        TranslationRequestEntity entity = factory.manufacturePojo(TranslationRequestEntity.class);
        TranslationRequestEntity result = translationRequestLogic.createTranslationRequest(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getCreationDate(), entity.getCreationDate());
        Assert.assertEquals(result.getDueDate(), entity.getDueDate());
        Assert.assertEquals(result.getContexto(), entity.getContexto());
        Assert.assertEquals(result.getEnlaceArchivoResultado(), entity.getEnlaceArchivoResultado());
    }

    /**
     * @generated
     */
    @Test
    public void getTranslationRequestsTest() {
        List<TranslationRequestEntity> list = translationRequestLogic.getTranslationRequests();
        Assert.assertEquals(data.size(), list.size());
        for (TranslationRequestEntity entity : list) {
            boolean found = false;
            for (TranslationRequestEntity storedEntity : data) {
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
    public void getTranslationRequestTest() {
        TranslationRequestEntity entity = data.get(0);
        TranslationRequestEntity resultEntity = translationRequestLogic.getTranslationRequest(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getCreationDate(), resultEntity.getCreationDate());
        Assert.assertEquals(entity.getDueDate(), resultEntity.getDueDate());
        Assert.assertEquals(entity.getContexto(), resultEntity.getContexto());
        Assert.assertEquals(entity.getEnlaceArchivoResultado(), resultEntity.getEnlaceArchivoResultado());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslationRequestTest() {
        TranslationRequestEntity entity = data.get(1);
        translationRequestLogic.deleteTranslationRequest(entity.getId());
        TranslationRequestEntity deleted = em.find(TranslationRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslationRequestTest() {
        TranslationRequestEntity entity = data.get(0);
        TranslationRequestEntity pojoEntity = factory.manufacturePojo(TranslationRequestEntity.class);

        pojoEntity.setId(entity.getId());

        translationRequestLogic.updateTranslationRequest(pojoEntity);

        TranslationRequestEntity resp = em.find(TranslationRequestEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getCreationDate(), resp.getCreationDate());
        Assert.assertEquals(pojoEntity.getDueDate(), resp.getDueDate());
        Assert.assertEquals(pojoEntity.getContexto(), resp.getContexto());
        Assert.assertEquals(pojoEntity.getEnlaceArchivoResultado(), resp.getEnlaceArchivoResultado());
    }
}
