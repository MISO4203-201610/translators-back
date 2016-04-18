package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.KnowledgeAreaLogic;
import co.edu.uniandes.csw.translationservice.api.IKnowledgeAreaLogic;
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
public class KnowledgeAreaLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IKnowledgeAreaLogic knowledgeAreaLogic;

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
    private List<KnowledgeAreaEntity> data = new ArrayList<KnowledgeAreaEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(KnowledgeAreaEntity.class.getPackage())
                .addPackage(KnowledgeAreaLogic.class.getPackage())
                .addPackage(IKnowledgeAreaLogic.class.getPackage())
                .addPackage(KnowledgeAreaPersistence.class.getPackage())
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
        em.createQuery("delete from KnowledgeAreaEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
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
        KnowledgeAreaEntity entity = factory.manufacturePojo(KnowledgeAreaEntity.class);
        KnowledgeAreaEntity result = knowledgeAreaLogic.createKnowledgeArea(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getKnowledgeAreasTest() {
        List<KnowledgeAreaEntity> list = knowledgeAreaLogic.getKnowledgeAreas();
        Assert.assertEquals(data.size(), list.size());
        for (KnowledgeAreaEntity entity : list) {
            boolean found = false;
            for (KnowledgeAreaEntity storedEntity : data) {
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
    public void getKnowledgeAreaTest() {
        KnowledgeAreaEntity entity = data.get(0);
        KnowledgeAreaEntity resultEntity = knowledgeAreaLogic.getKnowledgeArea(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteKnowledgeAreaTest() {
        KnowledgeAreaEntity entity = data.get(1);
        knowledgeAreaLogic.deleteKnowledgeArea(entity.getId());
        KnowledgeAreaEntity deleted = em.find(KnowledgeAreaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateKnowledgeAreaTest() {
        KnowledgeAreaEntity entity = data.get(0);
        KnowledgeAreaEntity pojoEntity = factory.manufacturePojo(KnowledgeAreaEntity.class);

        pojoEntity.setId(entity.getId());

        knowledgeAreaLogic.updateKnowledgeArea(pojoEntity);

        KnowledgeAreaEntity resp = em.find(KnowledgeAreaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
