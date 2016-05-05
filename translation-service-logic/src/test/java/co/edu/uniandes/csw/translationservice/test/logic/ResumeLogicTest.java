package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.api.IResumeLogic;
import co.edu.uniandes.csw.translationservice.ejbs.ResumeLogic;
import co.edu.uniandes.csw.translationservice.entities.ResumeEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.persistence.ResumePersistence;
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
public class ResumeLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IResumeLogic resumeLogic;

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
    private List<ResumeEntity> data = new ArrayList<ResumeEntity>();

    /**
     * @generated
     */
    private List<TranslatorEntity> translatorsData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResumeEntity.class.getPackage())
                .addPackage(ResumeLogic.class.getPackage())
                .addPackage(IResumeLogic.class.getPackage())
                .addPackage(ResumePersistence.class.getPackage())
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
        em.createQuery("delete from ResumeEntity").executeUpdate();
        em.createQuery("delete from TranslatorEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            TranslatorEntity translators = factory.manufacturePojo(TranslatorEntity.class);
            em.persist(translators);
            translatorsData.add(translators);
        }
        
        em.createQuery("delete from ResumeEntity").executeUpdate();
        for (int i = 0; i < 3; i++) {
            ResumeEntity entity = factory.manufacturePojo(ResumeEntity.class);

            entity.setTranslator(translatorsData.get(0));

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * @generated
     */
    @Test
    public void createResumeTest() {
        
        ResumeEntity entity = factory.manufacturePojo(ResumeEntity.class);
        ResumeEntity result = resumeLogic.createResume(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
     }

    /**
     * @generated
     */
    @Test
    public void getResumesTest() {
        List<ResumeEntity> list = resumeLogic.getResumes();
        Assert.assertEquals(data.size(), list.size());
        for (ResumeEntity entity : list) {
            boolean found = false;
            for (ResumeEntity storedEntity : data) {
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
    public void getResumeTest() {
        ResumeEntity entity = data.get(0);
        ResumeEntity resultEntity = resumeLogic.getResume(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteResumeTest() {
        ResumeEntity entity = data.get(1);
        resumeLogic.deleteResume(entity.getId());
        ResumeEntity deleted = em.find(ResumeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateResumeTest() {
        ResumeEntity entity = data.get(0);
        ResumeEntity pojoEntity = factory.manufacturePojo(ResumeEntity.class);

        pojoEntity.setId(entity.getId());

        resumeLogic.updateResume(pojoEntity);

        ResumeEntity resp = em.find(ResumeEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}