package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.TranslationOfferLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationOfferLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslationOfferPersistence;
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
public class TranslationOfferLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITranslationOfferLogic translationOfferLogic;

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
    private List<TranslationOfferEntity> data = new ArrayList<TranslationOfferEntity>();


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
                .addPackage(TranslationOfferEntity.class.getPackage())
                .addPackage(TranslationOfferLogic.class.getPackage())
                .addPackage(TranslationOfferLogic.class.getPackage())
                .addPackage(TranslationOfferPersistence.class.getPackage())
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
        em.createQuery("delete from TranslationOfferEntity").executeUpdate();
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
            TranslationOfferEntity entity = factory.manufacturePojo(TranslationOfferEntity.class);

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
    public void createTranslationOfferTest() {
        TranslationOfferEntity entity = factory.manufacturePojo(TranslationOfferEntity.class);
        TranslationOfferEntity result = translationOfferLogic.createTranslationOffer(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getPrice(), entity.getPrice());
        Assert.assertEquals(result.getComment(), entity.getComment());
    }

    /**
     * @generated
     */
    @Test
    public void getTranslationOffersTest() {
        List<TranslationOfferEntity> list = translationOfferLogic.getTranslationOffers();
        Assert.assertEquals(data.size(), list.size());
        for (TranslationOfferEntity entity : list) {
            boolean found = false;
            for (TranslationOfferEntity storedEntity : data) {
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
    public void getTranslationOfferTest() {
        TranslationOfferEntity entity = data.get(0);
        TranslationOfferEntity resultEntity = translationOfferLogic.getTranslationOffer(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
        Assert.assertEquals(entity.getComment(), resultEntity.getComment());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslationOfferTest() {
        TranslationOfferEntity entity = data.get(1);
        translationOfferLogic.deleteTranslationOffer(entity.getId());
        TranslationOfferEntity deleted = em.find(TranslationOfferEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslationOfferTest() {
        TranslationOfferEntity entity = data.get(0);
        TranslationOfferEntity pojoEntity = factory.manufacturePojo(TranslationOfferEntity.class);

        pojoEntity.setId(entity.getId());

        translationOfferLogic.updateTranslationOffer(pojoEntity);

        TranslationOfferEntity resp = em.find(TranslationOfferEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(pojoEntity.getComment(), resp.getComment());
    }
}
