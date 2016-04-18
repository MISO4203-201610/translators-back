package co.edu.uniandes.csw.translationservice.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import co.edu.uniandes.csw.translationservice.dtos.EducationDTO;
import co.edu.uniandes.csw.translationservice.dtos.KnowledgeAreaDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslationOfferDTO;
import co.edu.uniandes.csw.translationservice.dtos.LanguageDTO;
import co.edu.uniandes.csw.translationservice.dtos.ReviewDTO;
import co.edu.uniandes.csw.translationservice.services.TranslatorService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class TranslatorTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String translatorPath = "translators";
    private final static List<TranslatorDTO> oraculo = new ArrayList<>();
    private final String languagesPath = "languages";
    private final static List<LanguageDTO> oraculoLanguages = new ArrayList<>();
    private final String knowledgeAreasPath = "knowledgeAreas";
    private final static List<KnowledgeAreaDTO> oraculoKnowledgeAreas = new ArrayList<>();
    private final String translationOffersPath = "translationOffers";
    private final static List<TranslationOfferDTO> oraculoTranslationOffers = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");

    @ArquillianResource
    private URL deploymentURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw.translationservice:translation-service-logic:0.1.0")
                        .withTransitivity().asFile())
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw:auth-utils:0.1.0")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(TranslatorService.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        return ClientBuilder.newClient(config).target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() {
        insertData();
    }

    public static void insertData() {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            TranslatorDTO translator = factory.manufacturePojo(TranslatorDTO.class);
            translator.setId(i + 1L);
            List<EducationDTO> educationList = new ArrayList<>();
            for (int j = 0; j < 5; j++)
            {
                EducationDTO education = factory.manufacturePojo(EducationDTO.class);
                education.setId(i + 1L);
                educationList.add(education);
            }

            translator.setEducation(educationList);

            List<ReviewDTO> listReviews = new ArrayList<>();
            for (int j = 0; j < 5; j++)
            {
                ReviewDTO review = factory.manufacturePojo(ReviewDTO.class);
                review.setId(i + 1L);
                listReviews.add(review);
            }

            translator.setReviews(listReviews);

            oraculo.add(translator);

            LanguageDTO languages = factory.manufacturePojo(LanguageDTO.class);
            languages.setId(i + 1L);
            oraculoLanguages.add(languages);

            KnowledgeAreaDTO knowledgeAreas = factory.manufacturePojo(KnowledgeAreaDTO.class);
            knowledgeAreas.setId(i + 1L);
            oraculoKnowledgeAreas.add(knowledgeAreas);

            TranslationOfferDTO translationOffers = factory.manufacturePojo(TranslationOfferDTO.class);
            translationOffers.setId(i + 1L);
            oraculoTranslationOffers.add(translationOffers);
        }
    }

    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = target.path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    @Before
    public void setUpTest() {
        target = createWebTarget();
    }

    @Test
    @InSequence(1)
    public void createTranslatorTest() throws IOException {
        TranslatorDTO translator = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(translatorPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(translator, MediaType.APPLICATION_JSON));
        TranslatorDTO  translatorTest = (TranslatorDTO) response.readEntity(TranslatorDTO.class);
        Assert.assertEquals(translator.getId(), translatorTest.getId());
        Assert.assertEquals(translator.getName(), translatorTest.getName());
        Assert.assertEquals(translator.getPicture(), translatorTest.getPicture());
        Assert.assertEquals(translator.getBirthDate(), translatorTest.getBirthDate());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getTranslatorById() {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translatorTest = target.path(translatorPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(TranslatorDTO.class);
        Assert.assertEquals(translatorTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(translatorTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(translatorTest.getPicture(), oraculo.get(0).getPicture());
        Assert.assertEquals(translatorTest.getBirthDate(), oraculo.get(0).getBirthDate());
    }

    @Test
    @InSequence(3)
    public void listTranslatorTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(translatorPath)
                .request().cookie(cookieSessionId).get();
        String listTranslator = response.readEntity(String.class);
        List<TranslatorDTO> listTranslatorTest = new ObjectMapper().readValue(listTranslator, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(0, listTranslatorTest.size());
    }

    @Test
    @InSequence(4)
    public void updateTranslatorTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translator = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TranslatorDTO translatorChanged = factory.manufacturePojo(TranslatorDTO.class);
        translator.setName(translatorChanged.getName());
        translator.setEmail(translatorChanged.getEmail());
        translator.setPicture(translatorChanged.getPicture());
        translator.setBirthDate(translatorChanged.getBirthDate());
        Response response = target.path(translatorPath).path(translator.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(translator, MediaType.APPLICATION_JSON));
        TranslatorDTO translatorTest = (TranslatorDTO) response.readEntity(TranslatorDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(translator.getName(), translatorTest.getName());
        Assert.assertEquals(translator.getPicture(), translatorTest.getPicture());
        Assert.assertEquals(translator.getBirthDate(), translatorTest.getBirthDate());
    }

    @Test
    @InSequence(5)
    public void addLanguagesTest() {
        Cookie cookieSessionId = login(username, password);

        LanguageDTO languages = oraculoLanguages.get(0);
        TranslatorDTO translator = oraculo.get(0);


        Response response = target.path("languages")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(languages, MediaType.APPLICATION_JSON));

        LanguageDTO languagesTest = (LanguageDTO) response.readEntity(LanguageDTO.class);
        Assert.assertEquals(languages.getId(), languagesTest.getId());
        Assert.assertEquals(languages.getName(), languagesTest.getName());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(translatorPath).path(translator.getId().toString())
                .path(languagesPath).path(languages.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(languages, MediaType.APPLICATION_JSON));

        languagesTest = (LanguageDTO) response.readEntity(LanguageDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(languages.getId(), languagesTest.getId());
    }

    @Test
    @InSequence(6)
    public void listLanguagesTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translator = oraculo.get(0);

        Response response = target.path(translatorPath)
                .path(translator.getId().toString())
                .path(languagesPath)
                .request().cookie(cookieSessionId).get();

        String languagesList = response.readEntity(String.class);
        List<LanguageDTO> languagesListTest = new ObjectMapper().readValue(languagesList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, languagesListTest.size());
    }

    @Test
    @InSequence(7)
    public void getLanguagesTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        LanguageDTO languages = oraculoLanguages.get(0);
        TranslatorDTO translator = oraculo.get(0);

        LanguageDTO languagesTest = target.path(translatorPath)
                .path(translator.getId().toString()).path(languagesPath)
                .path(languages.getId().toString())
                .request().cookie(cookieSessionId).get(LanguageDTO.class);

        Assert.assertEquals(languages.getId(), languagesTest.getId());
        Assert.assertEquals(languages.getName(), languagesTest.getName());
    }

    @Test
    @InSequence(8)
    public void removeLanguagesTest() {
        Cookie cookieSessionId = login(username, password);

        LanguageDTO languages = oraculoLanguages.get(0);
        TranslatorDTO translator = oraculo.get(0);

        Response response = target.path(translatorPath).path(translator.getId().toString())
                .path(languagesPath).path(languages.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(9)
    public void addKnowledgeAreasTest() {
        Cookie cookieSessionId = login(username, password);

        KnowledgeAreaDTO knowledgeAreas = oraculoKnowledgeAreas.get(0);
        TranslatorDTO translator = oraculo.get(0);


        Response response = target.path("knowledgeAreas")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(knowledgeAreas, MediaType.APPLICATION_JSON));

        KnowledgeAreaDTO knowledgeAreasTest = (KnowledgeAreaDTO) response.readEntity(KnowledgeAreaDTO.class);
        Assert.assertEquals(knowledgeAreas.getId(), knowledgeAreasTest.getId());
        Assert.assertEquals(knowledgeAreas.getName(), knowledgeAreasTest.getName());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(translatorPath).path(translator.getId().toString())
                .path(knowledgeAreasPath).path(knowledgeAreas.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(knowledgeAreas, MediaType.APPLICATION_JSON));

        knowledgeAreasTest = (KnowledgeAreaDTO) response.readEntity(KnowledgeAreaDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(knowledgeAreas.getId(), knowledgeAreasTest.getId());
    }

    @Test
    @InSequence(10)
    public void listKnowledgeAreasTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translator = oraculo.get(0);

        Response response = target.path(translatorPath)
                .path(translator.getId().toString())
                .path(knowledgeAreasPath)
                .request().cookie(cookieSessionId).get();

        String knowledgeAreasList = response.readEntity(String.class);
        List<KnowledgeAreaDTO> knowledgeAreasListTest = new ObjectMapper().readValue(knowledgeAreasList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, knowledgeAreasListTest.size());
    }

    @Test
    @InSequence(11)
    public void getKnowledgeAreasTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        KnowledgeAreaDTO knowledgeAreas = oraculoKnowledgeAreas.get(0);
        TranslatorDTO translator = oraculo.get(0);

        KnowledgeAreaDTO knowledgeAreasTest = target.path(translatorPath)
                .path(translator.getId().toString()).path(knowledgeAreasPath)
                .path(knowledgeAreas.getId().toString())
                .request().cookie(cookieSessionId).get(KnowledgeAreaDTO.class);

        Assert.assertEquals(knowledgeAreas.getId(), knowledgeAreasTest.getId());
        Assert.assertEquals(knowledgeAreas.getName(), knowledgeAreasTest.getName());
    }

    @Test
    @InSequence(12)
    public void removeKnowledgeAreasTest() {
        Cookie cookieSessionId = login(username, password);

        KnowledgeAreaDTO knowledgeAreas = oraculoKnowledgeAreas.get(0);
        TranslatorDTO translator = oraculo.get(0);

        Response response = target.path(translatorPath).path(translator.getId().toString())
                .path(knowledgeAreasPath).path(knowledgeAreas.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(13)
    public void addTranslationOffersTest() {
        Cookie cookieSessionId = login(username, password);

        TranslationOfferDTO translationOffers = oraculoTranslationOffers.get(0);
        TranslatorDTO translator = oraculo.get(0);


        Response response = target.path("translationOffers")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(translationOffers, MediaType.APPLICATION_JSON));

        TranslationOfferDTO translationOffersTest = (TranslationOfferDTO) response.readEntity(TranslationOfferDTO.class);
        Assert.assertEquals(translationOffers.getId(), translationOffersTest.getId());
        Assert.assertEquals(translationOffers.getPrice(), translationOffersTest.getPrice());
        Assert.assertEquals(translationOffers.getComment(), translationOffersTest.getComment());

        response = target.path(translatorPath).path(translator.getId().toString())
                .path(translationOffersPath).path(translationOffers.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(translationOffers, MediaType.APPLICATION_JSON));

        translationOffersTest = (TranslationOfferDTO) response.readEntity(TranslationOfferDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(translationOffers.getId(), translationOffersTest.getId());
    }

    @Test
    @InSequence(14)
    public void listTranslationOffersTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translator = oraculo.get(0);

        Response response = target.path(translatorPath)
                .path(translator.getId().toString())
                .path(translationOffersPath)
                .request().cookie(cookieSessionId).get();

        String translationOffersList = response.readEntity(String.class);
        List<TranslationOfferDTO> translationOffersListTest = new ObjectMapper().readValue(translationOffersList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, translationOffersListTest.size());
    }

    @Test
    @InSequence(15)
    public void getTranslationOffersTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TranslationOfferDTO translationOffers = oraculoTranslationOffers.get(0);
        TranslatorDTO translator = oraculo.get(0);

        TranslationOfferDTO translationOffersTest = target.path(translatorPath)
                .path(translator.getId().toString()).path(translationOffersPath)
                .path(translationOffers.getId().toString())
                .request().cookie(cookieSessionId).get(TranslationOfferDTO.class);

        Assert.assertEquals(translationOffers.getId(), translationOffersTest.getId());
        Assert.assertEquals(translationOffers.getPrice(), translationOffersTest.getPrice());
        Assert.assertEquals(translationOffers.getComment(), translationOffersTest.getComment());
    }

    @Test
    @InSequence(16)
    public void removeTranslationOffersTest() {
        Cookie cookieSessionId = login(username, password);

        TranslationOfferDTO translationOffers = oraculoTranslationOffers.get(0);
        TranslatorDTO translator = oraculo.get(0);

        Response response = target.path(translatorPath).path(translator.getId().toString())
                .path(translationOffersPath).path(translationOffers.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(17)
    public void deleteTranslatorTest() {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translator = oraculo.get(0);
        Response response = target.path(translatorPath).path(translator.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}