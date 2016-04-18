package co.edu.uniandes.csw.translationservice.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.dtos.CustomerDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import co.edu.uniandes.csw.translationservice.services.CorrectionRequestService;
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
public class CorrectionRequestTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String correctionRequestPath = "correctionRequests";
    private final String customerPath = "customers";
    private final String translatorPath = "translators";
    private final static List<CorrectionRequestDTO> oraculo = new ArrayList<>();
    private final static List<TranslatorDTO> oraculoTranslator = new ArrayList<>();
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
                .addPackage(CorrectionRequestService.class.getPackage())
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
            
            CorrectionRequestDTO correctionRequest = factory.manufacturePojo(CorrectionRequestDTO.class);
            correctionRequest.setId(i + 1L);
            oraculo.add(correctionRequest);
            
            TranslatorDTO translator = factory.manufacturePojo(TranslatorDTO.class);
            translator.setId(i + 1L);
            oraculoTranslator.add(translator);

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
    public void createCorrectionRequestTest() throws IOException {
        PodamFactory factory = new PodamFactoryImpl();
        CustomerDTO customer = factory.manufacturePojo(CustomerDTO.class);;
        Cookie cookieSessionId = login(username, password);
        target.path(customerPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));

        CorrectionRequestDTO correctionRequest = oraculo.get(0);
        Response response = target.path(correctionRequestPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(correctionRequest, MediaType.APPLICATION_JSON));
        CorrectionRequestDTO correctionrequestTest = (CorrectionRequestDTO) response.readEntity(CorrectionRequestDTO.class);
        Assert.assertEquals(correctionRequest.getId(), correctionrequestTest.getId());
        Assert.assertEquals(correctionRequest.getName(), correctionrequestTest.getName());
        Assert.assertEquals(correctionRequest.getCreationDate(), correctionrequestTest.getCreationDate());
        Assert.assertEquals(correctionRequest.getDueDate(), correctionrequestTest.getDueDate());
//        Assert.assertEquals(correctionRequest.getDesctiption(), correctionrequestTest.getDesctiption());
//        Assert.assertEquals(correctionRequest.getNumberOfWords(), correctionrequestTest.getNumberOfWords());
//        Assert.assertEquals(correctionRequest.getKnowledgeAreas(), correctionrequestTest.getKnowledgeAreas());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getCorrectionRequestById() {
        Cookie cookieSessionId = login(username, password);
        CorrectionRequestDTO correctionrequestTest = target.path(correctionRequestPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(CorrectionRequestDTO.class);
        Assert.assertEquals(correctionrequestTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(correctionrequestTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(correctionrequestTest.getCreationDate(), oraculo.get(0).getCreationDate());
        Assert.assertEquals(correctionrequestTest.getDueDate(), oraculo.get(0).getDueDate());
//        Assert.assertEquals(correctionrequestTest.getDesctiption(), oraculo.get(0).getDesctiption());
//        Assert.assertEquals(correctionrequestTest.getNumberOfWords(), oraculo.get(0).getNumberOfWords());
    }

    @Test
    @InSequence(3)
    public void listCorrectionRequestTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(correctionRequestPath)
                .request().cookie(cookieSessionId).get();
        String listCorrectionRequest = response.readEntity(String.class);
        List<CorrectionRequestDTO> listCorrectionRequestTest = new ObjectMapper().readValue(listCorrectionRequest, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listCorrectionRequestTest.size());
    }

    @Test
    @InSequence(4)
    public void updateCorrectionRequestTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CorrectionRequestDTO correctionRequest = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CorrectionRequestDTO correctionRequestChanged = factory.manufacturePojo(CorrectionRequestDTO.class);
        correctionRequest.setName(correctionRequestChanged.getName());
        correctionRequest.setCreationDate(correctionRequestChanged.getCreationDate());
        correctionRequest.setDueDate(correctionRequestChanged.getDueDate());
        Response response = target.path(correctionRequestPath).path(correctionRequest.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(correctionRequest, MediaType.APPLICATION_JSON));
        CorrectionRequestDTO correctionrequestTest = (CorrectionRequestDTO) response.readEntity(CorrectionRequestDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(correctionRequest.getName(), correctionrequestTest.getName());
        Assert.assertEquals(correctionRequest.getCreationDate(), correctionrequestTest.getCreationDate());
        Assert.assertEquals(correctionRequest.getDueDate(), correctionrequestTest.getDueDate());
//        Assert.assertEquals(correctionRequest.getDesctiption(), correctionrequestTest.getDesctiption());
//        Assert.assertEquals(correctionRequest.getNumberOfWords(), correctionrequestTest.getNumberOfWords());
    }
    
    @Test
    @InSequence(5)
    public void getRecommendationsCorrectionRequestTest() {
        Cookie cookieSessionId = login(username, password);
        CorrectionRequestDTO correctionRequest = oraculo.get(0);
        Response response = target.path(correctionRequestPath).path("recommendations").path(correctionRequest.getId().toString())
                .request().cookie(cookieSessionId).get();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
    @Test
    @InSequence(6)
    public void sendInvitationRecommendationsCorrectionRequestTest() {
        Cookie cookieSessionId = login(username, password);
        CorrectionRequestDTO correctionRequest = oraculo.get(0);
        TranslatorDTO translator = oraculoTranslator.get(0);
        
        Response responseTranslator = target.path(translatorPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(translator, MediaType.APPLICATION_JSON));
        
        Response response = target.path(correctionRequestPath).path("recommendations").path(correctionRequest.getId().toString())
                .path("invite").path(translator.getId().toString())
                .request().cookie(cookieSessionId).get();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(7)
    public void deleteCorrectionRequestTest() {
        Cookie cookieSessionId = login(username, password);
        CorrectionRequestDTO correctionRequest = oraculo.get(0);
        Response response = target.path(correctionRequestPath).path(correctionRequest.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(Ok, response.getStatus());
    }
}
