package co.edu.uniandes.csw.translationservice.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.translationservice.dtos.LanguageDTO;
import co.edu.uniandes.csw.translationservice.services.LanguageService;
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
public class LanguageTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String languagePath = "languages";
    private final static List<LanguageDTO> oraculo = new ArrayList<>();
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
                .addPackage(LanguageService.class.getPackage())
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
            LanguageDTO language = factory.manufacturePojo(LanguageDTO.class);
            language.setId(i + 1L);

            oraculo.add(language);

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
    public void createLanguageTest() throws IOException {
        LanguageDTO language = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(languagePath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(language, MediaType.APPLICATION_JSON));
        LanguageDTO  languageTest = (LanguageDTO) response.readEntity(LanguageDTO.class);
        Assert.assertEquals(language.getId(), languageTest.getId());
        Assert.assertEquals(language.getName(), languageTest.getName());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getLanguageById() {
        Cookie cookieSessionId = login(username, password);
        LanguageDTO languageTest = target.path(languagePath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(LanguageDTO.class);
        Assert.assertEquals(languageTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(languageTest.getName(), oraculo.get(0).getName());
    }

    @Test
    @InSequence(3)
    public void listLanguageTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(languagePath)
                .request().cookie(cookieSessionId).get();
        String listLanguage = response.readEntity(String.class);
        List<LanguageDTO> listLanguageTest = new ObjectMapper().readValue(listLanguage, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listLanguageTest.size());
    }

    @Test
    @InSequence(4)
    public void updateLanguageTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        LanguageDTO language = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LanguageDTO languageChanged = factory.manufacturePojo(LanguageDTO.class);
        language.setName(languageChanged.getName());
        Response response = target.path(languagePath).path(language.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(language, MediaType.APPLICATION_JSON));
        LanguageDTO languageTest = (LanguageDTO) response.readEntity(LanguageDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(language.getName(), languageTest.getName());
    }

    @Test
    @InSequence(5)
    public void deleteLanguageTest() {
        Cookie cookieSessionId = login(username, password);
        LanguageDTO language = oraculo.get(0);
        Response response = target.path(languagePath).path(language.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
