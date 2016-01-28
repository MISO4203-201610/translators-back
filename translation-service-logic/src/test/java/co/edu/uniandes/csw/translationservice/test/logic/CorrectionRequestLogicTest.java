package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.CorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.persistence.CorrectionRequestPersistence;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.StatusEntity;
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
public class CorrectionRequestLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ICorrectionRequestLogic correctionRequestLogic;

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
    private List<CorrectionRequestEntity> data = new ArrayList<CorrectionRequestEntity>();

    /**
     * @generated
     */
    private List<LanguageEntity> languageData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CustomerEntity> customerData = new ArrayList<>();

    /**
     * @generated
     */
    private List<StatusEntity> statusData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CorrectionRequestEntity.class.getPackage())
                .addPackage(CorrectionRequestLogic.class.getPackage())
                .addPackage(ICorrectionRequestLogic.class.getPackage())
                .addPackage(CorrectionRequestPersistence.class.getPackage())
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
        em.createQuery("delete from CorrectionRequestEntity").executeUpdate();
        em.createQuery("delete from LanguageEntity").executeUpdate();
        em.createQuery("delete from CustomerEntity").executeUpdate();
        em.createQuery("delete from StatusEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LanguageEntity language = factory.manufacturePojo(LanguageEntity.class);
            em.persist(language);
            languageData.add(language);
        }

        for (int i = 0; i < 3; i++) {
            CustomerEntity customer = factory.manufacturePojo(CustomerEntity.class);
            em.persist(customer);
            customerData.add(customer);
        }

        for (int i = 0; i < 3; i++) {
            StatusEntity status = factory.manufacturePojo(StatusEntity.class);
            em.persist(status);
            statusData.add(status);
        }

        for (int i = 0; i < 3; i++) {
            CorrectionRequestEntity entity = factory.manufacturePojo(CorrectionRequestEntity.class);

            entity.setLanguage(languageData.get(0));

            entity.setCustomer(customerData.get(0));

            entity.setStatus(statusData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCorrectionRequestTest() {
        CorrectionRequestEntity entity = factory.manufacturePojo(CorrectionRequestEntity.class);
        CorrectionRequestEntity result = correctionRequestLogic.createCorrectionRequest(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getCreationDate(), entity.getCreationDate());
        Assert.assertEquals(result.getDueDate(), entity.getDueDate());
    }

    /**
     * @generated
     */
    @Test
    public void getCorrectionRequestsTest() {
        List<CorrectionRequestEntity> list = correctionRequestLogic.getCorrectionRequests();
        Assert.assertEquals(data.size(), list.size());
        for (CorrectionRequestEntity entity : list) {
            boolean found = false;
            for (CorrectionRequestEntity storedEntity : data) {
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
    public void getCorrectionRequestTest() {
        CorrectionRequestEntity entity = data.get(0);
        CorrectionRequestEntity resultEntity = correctionRequestLogic.getCorrectionRequest(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getCreationDate(), resultEntity.getCreationDate());
        Assert.assertEquals(entity.getDueDate(), resultEntity.getDueDate());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCorrectionRequestTest() {
        CorrectionRequestEntity entity = data.get(1);
        correctionRequestLogic.deleteCorrectionRequest(entity.getId());
        CorrectionRequestEntity deleted = em.find(CorrectionRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCorrectionRequestTest() {
        CorrectionRequestEntity entity = data.get(0);
        CorrectionRequestEntity pojoEntity = factory.manufacturePojo(CorrectionRequestEntity.class);

        pojoEntity.setId(entity.getId());

        correctionRequestLogic.updateCorrectionRequest(pojoEntity);

        CorrectionRequestEntity resp = em.find(CorrectionRequestEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getCreationDate(), resp.getCreationDate());
        Assert.assertEquals(pojoEntity.getDueDate(), resp.getDueDate());
    }
}
