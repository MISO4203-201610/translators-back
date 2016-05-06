package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.TranslatorOfertLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorOfertLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorOfertPersistence;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
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
public class TranslatorOfertLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITranslatorOfertLogic translatorOfertLogic;

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
    private List<TranslatorOfertEntity> data = new ArrayList<TranslatorOfertEntity>();


    /**
     * @generated
     */
    private List<TranslatorEntity> translatorData = new ArrayList<>();

    /**
     * @generated
     */
    private List<TranslationRequestEntity> translationRequestData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TranslatorOfertEntity.class.getPackage())
                .addPackage(TranslatorOfertLogic.class.getPackage())
                .addPackage(ITranslatorOfertLogic.class.getPackage())
                .addPackage(TranslatorOfertPersistence.class.getPackage())
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
        em.createQuery("delete from TranslatorOfertEntity").executeUpdate();
        em.createQuery("delete from TranslatorEntity").executeUpdate();
        em.createQuery("delete from TranslationRequestEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        

        for (int i = 0; i < 3; i++) {
            TranslatorEntity translator = factory.manufacturePojo(TranslatorEntity.class);
            em.persist(translator);
            translatorData.add(translator);
        }

        for (int i = 0; i < 3; i++) {
            TranslationRequestEntity translationRequest = factory.manufacturePojo(TranslationRequestEntity.class);
            em.persist(translationRequest);
            translationRequestData.add(translationRequest);
        }

        for (int i = 0; i < 3; i++) {
            TranslatorOfertEntity entity = factory.manufacturePojo(TranslatorOfertEntity.class);

            entity.setTranslator(translatorData.get(0));

            entity.setTranslationRequest(translationRequestData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTranslatorOfertTest() {
        TranslatorOfertEntity entity = factory.manufacturePojo(TranslatorOfertEntity.class);
        TranslatorOfertEntity result = translatorOfertLogic.createTranslatorOfert(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getPrice(), entity.getPrice());
        Assert.assertEquals(result.getComment(), entity.getComment());
        Assert.assertEquals(result.isAcceptedByCustomer(), entity.isAcceptedByCustomer());
        Assert.assertEquals(result.getStatus(), entity.getStatus());
        
    }

    /**
     * @generated
     */
    @Test
    public void getTranslatorOfertsTest() {
        List<TranslatorOfertEntity> list = translatorOfertLogic.getTranslatorOferts();
        Assert.assertEquals(data.size(), list.size());
        for (TranslatorOfertEntity entity : list) {
            boolean found = false;
            for (TranslatorOfertEntity storedEntity : data) {
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
    public void getTranslatorOfertTest() {
        TranslatorOfertEntity entity = data.get(0);
        TranslatorOfertEntity resultEntity = translatorOfertLogic.getTranslatorOfert(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
        Assert.assertEquals(entity.getComment(), resultEntity.getComment());
        Assert.assertEquals(entity.isAcceptedByCustomer(), resultEntity.isAcceptedByCustomer());
        Assert.assertEquals(entity.getStatus(), resultEntity.getStatus());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslatorOfertTest() {
        TranslatorOfertEntity entity = data.get(1);
        translatorOfertLogic.deleteTranslatorOfert(entity.getId());
        TranslatorOfertEntity deleted = em.find(TranslatorOfertEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslatorOfertTest() {
        TranslatorOfertEntity entity = data.get(0);
        TranslatorOfertEntity pojoEntity = factory.manufacturePojo(TranslatorOfertEntity.class);

        pojoEntity.setId(entity.getId());

        translatorOfertLogic.updateTranslatorOfert(pojoEntity);

        TranslatorOfertEntity resp = em.find(TranslatorOfertEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(pojoEntity.getComment(), resp.getComment());
        Assert.assertEquals(pojoEntity.isAcceptedByCustomer(), resp.isAcceptedByCustomer());
        Assert.assertEquals(pojoEntity.getStatus(), resp.getStatus());
    }
}
