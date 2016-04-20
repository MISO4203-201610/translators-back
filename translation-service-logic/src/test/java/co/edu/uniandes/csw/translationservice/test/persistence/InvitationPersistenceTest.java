package co.edu.uniandes.csw.translationservice.test.persistence;

import co.edu.uniandes.csw.translationservice.entities.InvitationEntity;
import co.edu.uniandes.csw.translationservice.persistence.InvitationPersistence;
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
public class InvitationPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InvitationEntity.class.getPackage())
                .addPackage(InvitationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private InvitationPersistence statusPersistence;

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
        em.createQuery("delete from InvitationEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<InvitationEntity> data = new ArrayList<InvitationEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            InvitationEntity entity = factory.manufacturePojo(InvitationEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createInvitationTest() {
		PodamFactory factory = new PodamFactoryImpl();
        InvitationEntity newEntity = factory.manufacturePojo(InvitationEntity.class);
        InvitationEntity result = statusPersistence.create(newEntity);

        Assert.assertNotNull(result);

        InvitationEntity entity = em.find(InvitationEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * @generated
     */
    @Test
    public void getInvitationsTest() {
        List<InvitationEntity> list = statusPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (InvitationEntity ent : list) {
            boolean found = false;
            for (InvitationEntity entity : data) {
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
    public void getInvitationTest() {
        InvitationEntity entity = data.get(0);
        InvitationEntity newEntity = statusPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * @generated
     */
    @Test
    public void deleteInvitationTest() {
        InvitationEntity entity = data.get(0);
        statusPersistence.delete(entity.getId());
        InvitationEntity deleted = em.find(InvitationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateInvitationTest() {
        InvitationEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InvitationEntity newEntity = factory.manufacturePojo(InvitationEntity.class);

        newEntity.setId(entity.getId());

        statusPersistence.update(newEntity);

        InvitationEntity resp = em.find(InvitationEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
