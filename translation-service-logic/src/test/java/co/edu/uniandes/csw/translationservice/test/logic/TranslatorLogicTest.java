package co.edu.uniandes.csw.translationservice.test.logic;

import co.edu.uniandes.csw.translationservice.ejbs.TranslatorLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorPersistence;
import co.edu.uniandes.csw.translationservice.entities.EducationEntity;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
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
public class TranslatorLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITranslatorLogic translatorLogic;

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
    private List<TranslatorEntity> data = new ArrayList<TranslatorEntity>();

    /**
     * @generated
     */
    private List<LanguageEntity> languagesData = new ArrayList<>();
    
    private List<KnowledgeAreaEntity> knowledgeAreasData = new ArrayList<>();
    
    /**
     * @generated
     */
    private List<TranslationOfferEntity> translationOffersData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TranslatorEntity.class.getPackage())
                .addPackage(TranslatorLogic.class.getPackage())
                .addPackage(ITranslatorLogic.class.getPackage())
                .addPackage(TranslatorPersistence.class.getPackage())
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
        em.createQuery("delete from EducationEntity").executeUpdate();
        em.createQuery("delete from TranslatorEntity").executeUpdate();
        em.createQuery("delete from LanguageEntity").executeUpdate();
        em.createQuery("delete from KnowledgeAreaEntity").executeUpdate();
        em.createQuery("delete from TranslationOfferEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LanguageEntity languages = factory.manufacturePojo(LanguageEntity.class);
            em.persist(languages);
            languagesData.add(languages);
        }
        
        for (int i = 0; i < 3; i++) {
            KnowledgeAreaEntity knowledgeAreas = factory.manufacturePojo(KnowledgeAreaEntity.class);
            em.persist(knowledgeAreas);
            knowledgeAreasData.add(knowledgeAreas);
        }

        for (int i = 0; i < 3; i++) {
            TranslationOfferEntity translationOffers = factory.manufacturePojo(TranslationOfferEntity.class);
            em.persist(translationOffers);
            translationOffersData.add(translationOffers);
        }
        
        for (int i = 0; i < 3; i++) {
            TranslatorEntity entity = factory.manufacturePojo(TranslatorEntity.class);

            for (EducationEntity item : entity.getEducation()) {
                item.setTranslator(entity);
            }
            
            if (i == 0) {
                translationOffersData.get(i).setTranslator(entity);
            }

            entity.getLanguages().add(languagesData.get(0));
            entity.getKnowledgeAreas().add(knowledgeAreasData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createTranslatorTest() {
        TranslatorEntity entity = factory.manufacturePojo(TranslatorEntity.class);
        TranslatorEntity result = translatorLogic.createTranslator(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getPicture(), entity.getPicture());
        Assert.assertEquals(result.getBirthDate(), entity.getBirthDate());
        
        for (EducationEntity entityEducation : entity.getEducation()) {
            for (EducationEntity resultEducation : result.getEducation()) {
                Assert.assertEquals(entityEducation.getDescription(), resultEducation.getDescription());
                Assert.assertEquals(entityEducation.getInstitution(), resultEducation.getInstitution());
                Assert.assertEquals(entityEducation.getName(), resultEducation.getName());
                Assert.assertEquals(entityEducation.getTranslator(), resultEducation.getTranslator());
                Assert.assertEquals(entityEducation.getTitle(), resultEducation.getTitle());
                Assert.assertEquals(entityEducation.getStartDate(), resultEducation.getStartDate());
                Assert.assertEquals(entityEducation.getEndDate(), resultEducation.getEndDate());
            }
        }
    }

    /**
     * @generated
     */
    @Test
    public void getTranslatorsTest() {
        List<TranslatorEntity> list = translatorLogic.getTranslators();
        Assert.assertEquals(data.size(), list.size());
        for (TranslatorEntity entity : list) {
            boolean found = false;
            for (TranslatorEntity storedEntity : data) {
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
    public void getTranslatorTest() {
        TranslatorEntity entity = data.get(0);
        TranslatorEntity resultEntity = translatorLogic.getTranslator(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getPicture(), resultEntity.getPicture());
        Assert.assertEquals(entity.getBirthDate(), resultEntity.getBirthDate());
    }

    /**
     * @generated
     */
    @Test
    public void deleteTranslatorTest() {
        TranslatorEntity entity = data.get(1);
        translatorLogic.deleteTranslator(entity.getId());
        TranslatorEntity deleted = em.find(TranslatorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateTranslatorTest() {
        TranslatorEntity entity = data.get(0);
        TranslatorEntity pojoEntity = factory.manufacturePojo(TranslatorEntity.class);

        pojoEntity.setId(entity.getId());

        translatorLogic.updateTranslator(pojoEntity);

        TranslatorEntity resp = em.find(TranslatorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getPicture(), resp.getPicture());
        Assert.assertEquals(pojoEntity.getBirthDate(), resp.getBirthDate());
    }

    /**
     * @generated
     */
    @Test
    public void getLanguagesTest() {
        TranslatorEntity entity = data.get(0);
        LanguageEntity languageEntity = languagesData.get(0);
        LanguageEntity response = translatorLogic.getLanguages(entity.getId(), languageEntity.getId());

        Assert.assertEquals(languageEntity.getId(), response.getId());
        Assert.assertEquals(languageEntity.getName(), response.getName());
    }

    /**
     * @generated
     */
    @Test
    public void listLanguagesTest() {
        List<LanguageEntity> list = translatorLogic.listLanguages(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addLanguagesTest() {
        TranslatorEntity entity = data.get(0);
        LanguageEntity languageEntity = languagesData.get(1);
        LanguageEntity response = translatorLogic.addLanguages(entity.getId(), languageEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(languageEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceLanguagesTest() {
        TranslatorEntity entity = data.get(0);
        List<LanguageEntity> list = languagesData.subList(1, 3);
        translatorLogic.replaceLanguages(entity.getId(), list);

        entity = translatorLogic.getTranslator(entity.getId());
        Assert.assertFalse(entity.getLanguages().contains(languagesData.get(0)));
        Assert.assertTrue(entity.getLanguages().contains(languagesData.get(1)));
        Assert.assertTrue(entity.getLanguages().contains(languagesData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeLanguagesTest() {
        translatorLogic.removeLanguages(data.get(0).getId(), languagesData.get(0).getId());
        LanguageEntity response = translatorLogic.getLanguages(data.get(0).getId(), languagesData.get(0).getId());
        Assert.assertNull(response);
    }
    
    @Test
    public void getKnowledgeAreasTest() {
        TranslatorEntity entity = data.get(0);
        KnowledgeAreaEntity knowledgeAreaEntity = knowledgeAreasData.get(0);
        KnowledgeAreaEntity response = translatorLogic.getKnowledgeAreas(entity.getId(), knowledgeAreaEntity.getId());

        Assert.assertEquals(knowledgeAreaEntity.getId(), response.getId());
        Assert.assertEquals(knowledgeAreaEntity.getName(), response.getName());
    }

    /**
     * @generated
     */
    @Test
    public void listKnowledgeAreasTest() {
        List<KnowledgeAreaEntity> list = translatorLogic.listKnowledgeAreas(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addKnowledgeAreasTest() {
        TranslatorEntity entity = data.get(0);
        KnowledgeAreaEntity knowledgeAreaEntity = knowledgeAreasData.get(1);
        KnowledgeAreaEntity response = translatorLogic.addKnowledgeAreas(entity.getId(), knowledgeAreaEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(knowledgeAreaEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceKnowledgeAreasTest() {
        TranslatorEntity entity = data.get(0);
        List<KnowledgeAreaEntity> list = knowledgeAreasData.subList(1, 3);
        translatorLogic.replaceKnowledgeAreas(entity.getId(), list);

        entity = translatorLogic.getTranslator(entity.getId());
        Assert.assertFalse(entity.getKnowledgeAreas().contains(knowledgeAreasData.get(0)));
        Assert.assertTrue(entity.getKnowledgeAreas().contains(knowledgeAreasData.get(1)));
        Assert.assertTrue(entity.getKnowledgeAreas().contains(knowledgeAreasData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeKnowledgeAreasTest() {
        translatorLogic.removeKnowledgeAreas(data.get(0).getId(), knowledgeAreasData.get(0).getId());
        KnowledgeAreaEntity response = translatorLogic.getKnowledgeAreas(data.get(0).getId(), knowledgeAreasData.get(0).getId());
        Assert.assertNull(response);
    }
    
    /**
     * @generated
     */
    @Test
    public void getTranslationOffersTest() {
        TranslatorEntity entity = data.get(0);
        TranslationOfferEntity translationOfferEntity = translationOffersData.get(0);
        TranslationOfferEntity response = translatorLogic.getTranslationOffers(entity.getId(), translationOfferEntity.getId());

        Assert.assertEquals(translationOfferEntity.getId(), response.getId());
        Assert.assertEquals(translationOfferEntity.getPrice(), response.getPrice());
        Assert.assertEquals(translationOfferEntity.getComment(), response.getComment());
    }

    /**
     * @generated
     */
    @Test
    public void listTranslationOffersTest() {
        List<TranslationOfferEntity> list = translatorLogic.listTranslationOffers(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addTranslationOffersTest() {
        TranslatorEntity entity = data.get(0);
        TranslationOfferEntity translationOfferEntity = translationOffersData.get(1);
        TranslationOfferEntity response = translatorLogic.addTranslationOffers(entity.getId(), translationOfferEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(translationOfferEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceTranslationOffersTest() {
        TranslatorEntity entity = data.get(0);
        List<TranslationOfferEntity> list = translationOffersData.subList(1, 3);
        translatorLogic.replaceTranslationOffers(entity.getId(), list);

        entity = translatorLogic.getTranslator(entity.getId());
        Assert.assertFalse(entity.getTranslationOffers().contains(translationOffersData.get(0)));
        Assert.assertTrue(entity.getTranslationOffers().contains(translationOffersData.get(1)));
        Assert.assertTrue(entity.getTranslationOffers().contains(translationOffersData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeTranslationOffersTest() {
        translatorLogic.removeTranslationOffers(data.get(0).getId(), translationOffersData.get(0).getId());
        TranslationOfferEntity response = translatorLogic.getTranslationOffers(data.get(0).getId(), translationOffersData.get(0).getId());
        Assert.assertNull(response);
    }
}
