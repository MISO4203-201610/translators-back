package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.api.ITrajectoryLogic;
import co.edu.uniandes.csw.translationservice.ejbs.TrajectoryLogic;
import co.edu.uniandes.csw.translationservice.entities.TrajectoryEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.persistence.TrajectoryPersistence;
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

@RunWith(Arquillian.class)
public class TrajectoryLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITrajectoryLogic trajectoryLogic;

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
    private List<TrajectoryEntity> data = new ArrayList<TrajectoryEntity>();

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
                .addPackage(TrajectoryEntity.class.getPackage())
                .addPackage(TrajectoryLogic.class.getPackage())
                .addPackage(ITrajectoryLogic.class.getPackage())
                .addPackage(TrajectoryPersistence.class.getPackage())
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
        em.createQuery("delete from TrajectoryEntity").executeUpdate();
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
        
        em.createQuery("delete from CurriculumEntity").executeUpdate();
        for (int i = 0; i < 3; i++) {
            TrajectoryEntity entity = factory.manufacturePojo(TrajectoryEntity.class);

            entity.setTranslator(translatorsData.get(0));

            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * @generated
     */
    @Test
    public void createWorkExperienceTest() {
        
        TrajectoryEntity entity = factory.manufacturePojo(TrajectoryEntity.class);
        TrajectoryEntity result = trajectoryLogic.createTrajectory(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
     }

    /**
     * @generated
     */
    @Test
    public void getCurriculumsTest() {
        List<TrajectoryEntity> list = trajectoryLogic.getTrajectory();
        Assert.assertEquals(data.size(), list.size());
        for (TrajectoryEntity entity : list) {
            boolean found = false;
            for (TrajectoryEntity storedEntity : data) {
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
    public void getWorkExperienceTest() {
        TrajectoryEntity entity = data.get(0);
        TrajectoryEntity resultEntity = trajectoryLogic.getTrajectory(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteWorkExperienceTest() {
        TrajectoryEntity entity = data.get(1);
        trajectoryLogic.deleteTrajectory(entity.getId());
        TrajectoryEntity deleted = em.find(TrajectoryEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateWorkExperienceTest() {
        TrajectoryEntity entity = data.get(0);
        TrajectoryEntity pojoEntity = factory.manufacturePojo(TrajectoryEntity.class);

        pojoEntity.setId(entity.getId());

        trajectoryLogic.updateTrajectory(pojoEntity);

        TrajectoryEntity resp = em.find(TrajectoryEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
