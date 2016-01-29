package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.CustomerLogic;
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.persistence.CustomerPersistence;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
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
public class CustomerLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ICustomerLogic customerLogic;

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
    private List<CustomerEntity> data = new ArrayList<CustomerEntity>();

    /**
     * @generated
     */
    private List<TranslationRequestEntity> translationRequestsData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CorrectionRequestEntity> correctionRequestsData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CustomerEntity.class.getPackage())
                .addPackage(CustomerLogic.class.getPackage())
                .addPackage(ICustomerLogic.class.getPackage())
                .addPackage(CustomerPersistence.class.getPackage())
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
        em.createQuery("delete from CorrectionRequestEntity").executeUpdate();
        em.createQuery("delete from CustomerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TranslationRequestEntity translationRequests = factory.manufacturePojo(TranslationRequestEntity.class);
            em.persist(translationRequests);
            translationRequestsData.add(translationRequests);
        }

        for (int i = 0; i < 3; i++) {
            CorrectionRequestEntity correctionRequests = factory.manufacturePojo(CorrectionRequestEntity.class);
            em.persist(correctionRequests);
            correctionRequestsData.add(correctionRequests);
        }

        for (int i = 0; i < 3; i++) {
            CustomerEntity entity = factory.manufacturePojo(CustomerEntity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                translationRequestsData.get(i).setCustomer(entity);
            }

            if (i == 0) {
                correctionRequestsData.get(i).setCustomer(entity);
            }
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCustomerTest() {
        CustomerEntity entity = factory.manufacturePojo(CustomerEntity.class);
        CustomerEntity result = customerLogic.createCustomer(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getCustomersTest() {
        List<CustomerEntity> list = customerLogic.getCustomers();
        Assert.assertEquals(data.size(), list.size());
        for (CustomerEntity entity : list) {
            boolean found = false;
            for (CustomerEntity storedEntity : data) {
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
    public void getCustomerTest() {
        CustomerEntity entity = data.get(0);
        CustomerEntity resultEntity = customerLogic.getCustomer(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCustomerTest() {
        CustomerEntity entity = data.get(1);
        customerLogic.deleteCustomer(entity.getId());
        CustomerEntity deleted = em.find(CustomerEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCustomerTest() {
        CustomerEntity entity = data.get(0);
        CustomerEntity pojoEntity = factory.manufacturePojo(CustomerEntity.class);

        pojoEntity.setId(entity.getId());

        customerLogic.updateCustomer(pojoEntity);

        CustomerEntity resp = em.find(CustomerEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getTranslationRequestsTest() {
        CustomerEntity entity = data.get(0);
        TranslationRequestEntity translationRequestEntity = translationRequestsData.get(0);
        TranslationRequestEntity response = customerLogic.getTranslationRequests(entity.getId(), translationRequestEntity.getId());

        Assert.assertEquals(translationRequestEntity.getId(), response.getId());
        Assert.assertEquals(translationRequestEntity.getName(), response.getName());
        Assert.assertEquals(translationRequestEntity.getCreationDate(), response.getCreationDate());
        Assert.assertEquals(translationRequestEntity.getDueDate(), response.getDueDate());
    }

    /**
     * @generated
     */
    @Test
    public void listTranslationRequestsTest() {
        List<TranslationRequestEntity> list = customerLogic.listTranslationRequests(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addTranslationRequestsTest() {
        CustomerEntity entity = data.get(0);
        TranslationRequestEntity translationRequestEntity = translationRequestsData.get(1);
        TranslationRequestEntity response = customerLogic.addTranslationRequests(entity.getId(), translationRequestEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(translationRequestEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceTranslationRequestsTest() {
        CustomerEntity entity = data.get(0);
        List<TranslationRequestEntity> list = translationRequestsData.subList(1, 3);
        customerLogic.replaceTranslationRequests(entity.getId(), list);

        entity = customerLogic.getCustomer(entity.getId());
        Assert.assertFalse(entity.getTranslationRequests().contains(translationRequestsData.get(0)));
        Assert.assertTrue(entity.getTranslationRequests().contains(translationRequestsData.get(1)));
        Assert.assertTrue(entity.getTranslationRequests().contains(translationRequestsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeTranslationRequestsTest() {
        customerLogic.removeTranslationRequests(data.get(0).getId(), translationRequestsData.get(0).getId());
        TranslationRequestEntity response = customerLogic.getTranslationRequests(data.get(0).getId(), translationRequestsData.get(0).getId());
        Assert.assertNull(response);
    }

    /**
     * @generated
     */
    @Test
    public void getCorrectionRequestsTest() {
        CustomerEntity entity = data.get(0);
        CorrectionRequestEntity correctionRequestEntity = correctionRequestsData.get(0);
        CorrectionRequestEntity response = customerLogic.getCorrectionRequests(entity.getId(), correctionRequestEntity.getId());

        Assert.assertEquals(correctionRequestEntity.getId(), response.getId());
        Assert.assertEquals(correctionRequestEntity.getName(), response.getName());
        Assert.assertEquals(correctionRequestEntity.getCreationDate(), response.getCreationDate());
        Assert.assertEquals(correctionRequestEntity.getDueDate(), response.getDueDate());
    }

    /**
     * @generated
     */
    @Test
    public void listCorrectionRequestsTest() {
        List<CorrectionRequestEntity> list = customerLogic.listCorrectionRequests(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addCorrectionRequestsTest() {
        CustomerEntity entity = data.get(0);
        CorrectionRequestEntity correctionRequestEntity = correctionRequestsData.get(1);
        CorrectionRequestEntity response = customerLogic.addCorrectionRequests(entity.getId(), correctionRequestEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(correctionRequestEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceCorrectionRequestsTest() {
        CustomerEntity entity = data.get(0);
        List<CorrectionRequestEntity> list = correctionRequestsData.subList(1, 3);
        customerLogic.replaceCorrectionRequests(entity.getId(), list);

        entity = customerLogic.getCustomer(entity.getId());
        Assert.assertFalse(entity.getCorrectionRequests().contains(correctionRequestsData.get(0)));
        Assert.assertTrue(entity.getCorrectionRequests().contains(correctionRequestsData.get(1)));
        Assert.assertTrue(entity.getCorrectionRequests().contains(correctionRequestsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeCorrectionRequestsTest() {
        customerLogic.removeCorrectionRequests(data.get(0).getId(), correctionRequestsData.get(0).getId());
        CorrectionRequestEntity response = customerLogic.getCorrectionRequests(data.get(0).getId(), correctionRequestsData.get(0).getId());
        Assert.assertNull(response);
    }
}
