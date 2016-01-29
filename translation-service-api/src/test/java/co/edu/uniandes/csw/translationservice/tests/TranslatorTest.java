package co.edu.uniandes.csw.translationservice.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import co.edu.uniandes.csw.translationservice.dtos.EducationDTO;
import co.edu.uniandes.csw.translationservice.dtos.LanguageDTO;
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

            oraculo.add(translator);

            LanguageDTO languages = factory.manufacturePojo(LanguageDTO.class);
            languages.setId(i + 1L);
            oraculoLanguages.add(languages);
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
        Assert.assertEquals(1, listTranslatorTest.size());
    }

    @Test
    @InSequence(4)
    public void updateTranslatorTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translator = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TranslatorDTO translatorChanged = factory.manufacturePojo(TranslatorDTO.class);
        translator.setName(translatorChanged.getName());
        Response response = target.path(translatorPath).path(translator.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(translator, MediaType.APPLICATION_JSON));
        TranslatorDTO translatorTest = (TranslatorDTO) response.readEntity(TranslatorDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(translator.getName(), translatorTest.getName());
    }

    @Test
    @InSequence(9)
    public void deleteTranslatorTest() {
        Cookie cookieSessionId = login(username, password);
        TranslatorDTO translator = oraculo.get(0);
        Response response = target.path(translatorPath).path(translator.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
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
}
