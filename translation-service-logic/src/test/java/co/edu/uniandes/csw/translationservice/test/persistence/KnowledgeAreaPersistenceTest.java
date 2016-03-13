package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.persistence.KnowledgeAreaPersistence;
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
public class KnowledgeAreaPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(KnowledgeAreaEntity.class.getPackage())
                .addPackage(KnowledgeAreaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private KnowledgeAreaPersistence knowledgeAreaPersistence;

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
        em.createQuery("delete from KnowledgeAreaEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<KnowledgeAreaEntity> data = new ArrayList<KnowledgeAreaEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            KnowledgeAreaEntity entity = factory.manufacturePojo(KnowledgeAreaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createKnowledgeAreaTest() {
		PodamFactory factory = new PodamFactoryImpl();
        KnowledgeAreaEntity newEntity = factory.manufacturePojo(KnowledgeAreaEntity.class);
        KnowledgeAreaEntity result = knowledgeAreaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        KnowledgeAreaEntity entity = em.find(KnowledgeAreaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getKnowledgeAreasTest() {
        List<KnowledgeAreaEntity> list = knowledgeAreaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (KnowledgeAreaEntity ent : list) {
            boolean found = false;
            for (KnowledgeAreaEntity entity : data) {
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
    public void getKnowledgeAreaTest() {
        KnowledgeAreaEntity entity = data.get(0);
        KnowledgeAreaEntity newEntity = knowledgeAreaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteKnowledgeAreaTest() {
        KnowledgeAreaEntity entity = data.get(0);
        knowledgeAreaPersistence.delete(entity.getId());
        KnowledgeAreaEntity deleted = em.find(KnowledgeAreaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateKnowledgeAreaTest() {
        KnowledgeAreaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        KnowledgeAreaEntity newEntity = factory.manufacturePojo(KnowledgeAreaEntity.class);

        newEntity.setId(entity.getId());

        knowledgeAreaPersistence.update(newEntity);

        KnowledgeAreaEntity resp = em.find(KnowledgeAreaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
