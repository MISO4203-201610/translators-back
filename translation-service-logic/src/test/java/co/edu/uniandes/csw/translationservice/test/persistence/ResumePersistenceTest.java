package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.ResumeEntity;
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
public class ResumePersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResumeEntity.class.getPackage())
                .addPackage(ResumePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ResumePersistence resumePersistence;

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
        em.createQuery("delete from ResumeEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ResumeEntity> data = new ArrayList<ResumeEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ResumeEntity entity = factory.manufacturePojo(ResumeEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createResumeTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ResumeEntity newEntity = factory.manufacturePojo(ResumeEntity.class);
        ResumeEntity result = resumePersistence.create(newEntity);

        Assert.assertNotNull(result);

        ResumeEntity entity = em.find(ResumeEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getResumesTest() {
        List<ResumeEntity> list = resumePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ResumeEntity ent : list) {
            boolean found = false;
            for (ResumeEntity entity : data) {
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
    public void getResumeTest() {
        ResumeEntity entity = data.get(0);
        ResumeEntity newEntity = resumePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteResumeTest() {
        ResumeEntity entity = data.get(0);
        resumePersistence.delete(entity.getId());
        ResumeEntity deleted = em.find(ResumeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateResumeTest() {
        ResumeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ResumeEntity newEntity = factory.manufacturePojo(ResumeEntity.class);

        newEntity.setId(entity.getId());

        resumePersistence.update(newEntity);

        ResumeEntity resp = em.find(ResumeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}