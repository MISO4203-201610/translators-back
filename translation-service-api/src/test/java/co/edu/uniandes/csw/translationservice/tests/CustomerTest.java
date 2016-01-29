package co.edu.uniandes.csw.translationservice.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.translationservice.dtos.CustomerDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.services.CustomerService;
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
public class CustomerTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String customerPath = "customers";
    private final static List<CustomerDTO> oraculo = new ArrayList<>();
    private final String translationRequestsPath = "translationRequests";
    private final static List<TranslationRequestDTO> oraculoTranslationRequests = new ArrayList<>();
    private final String correctionRequestsPath = "correctionRequests";
    private final static List<CorrectionRequestDTO> oraculoCorrectionRequests = new ArrayList<>();
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
                        .resolve("co.edu.uniandes.csw.translationservice:translation-service-logic:0.1.0-SNAPSHOT")
                        .withTransitivity().asFile())
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw:auth-utils:0.1.0")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(CustomerService.class.getPackage())
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
            CustomerDTO customer = factory.manufacturePojo(CustomerDTO.class);
            customer.setId(i + 1L);

            oraculo.add(customer);

            TranslationRequestDTO translationRequests = factory.manufacturePojo(TranslationRequestDTO.class);
            translationRequests.setId(i + 1L);
            oraculoTranslationRequests.add(translationRequests);
            CorrectionRequestDTO correctionRequests = factory.manufacturePojo(CorrectionRequestDTO.class);
            correctionRequests.setId(i + 1L);
            oraculoCorrectionRequests.add(correctionRequests);
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
    public void createCustomerTest() throws IOException {
        CustomerDTO customer = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(customerPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));
        CustomerDTO  customerTest = (CustomerDTO) response.readEntity(CustomerDTO.class);
        Assert.assertEquals(customer.getId(), customerTest.getId());
        Assert.assertEquals(customer.getName(), customerTest.getName());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getCustomerById() {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customerTest = target.path(customerPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(CustomerDTO.class);
        Assert.assertEquals(customerTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(customerTest.getName(), oraculo.get(0).getName());
    }

    @Test
    @InSequence(3)
    public void listCustomerTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(customerPath)
                .request().cookie(cookieSessionId).get();
        String listCustomer = response.readEntity(String.class);
        List<CustomerDTO> listCustomerTest = new ObjectMapper().readValue(listCustomer, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listCustomerTest.size());
    }

    @Test
    @InSequence(4)
    public void updateCustomerTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customer = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CustomerDTO customerChanged = factory.manufacturePojo(CustomerDTO.class);
        customer.setName(customerChanged.getName());
        Response response = target.path(customerPath).path(customer.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(customer, MediaType.APPLICATION_JSON));
        CustomerDTO customerTest = (CustomerDTO) response.readEntity(CustomerDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(customer.getName(), customerTest.getName());
    }

    @Test
    @InSequence(13)
    public void deleteCustomerTest() {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customer = oraculo.get(0);
        Response response = target.path(customerPath).path(customer.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void addTranslationRequestsTest() {
        Cookie cookieSessionId = login(username, password);

        TranslationRequestDTO translationRequests = oraculoTranslationRequests.get(0);
        CustomerDTO customer = oraculo.get(0);


        Response response = target.path("translationRequests")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(translationRequests, MediaType.APPLICATION_JSON));

        TranslationRequestDTO translationrequestsTest = (TranslationRequestDTO) response.readEntity(TranslationRequestDTO.class);
        Assert.assertEquals(translationRequests.getId(), translationrequestsTest.getId());
        Assert.assertEquals(translationRequests.getName(), translationrequestsTest.getName());
        Assert.assertEquals(translationRequests.getCreationDate(), translationrequestsTest.getCreationDate());
        Assert.assertEquals(translationRequests.getDueDate(), translationrequestsTest.getDueDate());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(customerPath).path(customer.getId().toString())
                .path(translationRequestsPath).path(translationRequests.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(translationRequests, MediaType.APPLICATION_JSON));

        translationrequestsTest = (TranslationRequestDTO) response.readEntity(TranslationRequestDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(translationRequests.getId(), translationrequestsTest.getId());
    }

    @Test
    @InSequence(6)
    public void listTranslationRequestsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customer = oraculo.get(0);

        Response response = target.path(customerPath)
                .path(customer.getId().toString())
                .path(translationRequestsPath)
                .request().cookie(cookieSessionId).get();

        String translationRequestsList = response.readEntity(String.class);
        List<TranslationRequestDTO> translationRequestsListTest = new ObjectMapper().readValue(translationRequestsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, translationRequestsListTest.size());
    }

    @Test
    @InSequence(7)
    public void getTranslationRequestsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TranslationRequestDTO translationRequests = oraculoTranslationRequests.get(0);
        CustomerDTO customer = oraculo.get(0);

        TranslationRequestDTO translationrequestsTest = target.path(customerPath)
                .path(customer.getId().toString()).path(translationRequestsPath)
                .path(translationRequests.getId().toString())
                .request().cookie(cookieSessionId).get(TranslationRequestDTO.class);

        Assert.assertEquals(translationRequests.getId(), translationrequestsTest.getId());
        Assert.assertEquals(translationRequests.getName(), translationrequestsTest.getName());
        Assert.assertEquals(translationRequests.getCreationDate(), translationrequestsTest.getCreationDate());
        Assert.assertEquals(translationRequests.getDueDate(), translationrequestsTest.getDueDate());
    }

    @Test
    @InSequence(8)
    public void removeTranslationRequestsTest() {
        Cookie cookieSessionId = login(username, password);

        TranslationRequestDTO translationRequests = oraculoTranslationRequests.get(0);
        CustomerDTO customer = oraculo.get(0);

        Response response = target.path(customerPath).path(customer.getId().toString())
                .path(translationRequestsPath).path(translationRequests.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(9)
    public void addCorrectionRequestsTest() {
        Cookie cookieSessionId = login(username, password);

        CorrectionRequestDTO correctionRequests = oraculoCorrectionRequests.get(0);
        CustomerDTO customer = oraculo.get(0);


        Response response = target.path("correctionRequests")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(correctionRequests, MediaType.APPLICATION_JSON));

        CorrectionRequestDTO correctionrequestsTest = (CorrectionRequestDTO) response.readEntity(CorrectionRequestDTO.class);
        Assert.assertEquals(correctionRequests.getId(), correctionrequestsTest.getId());
        Assert.assertEquals(correctionRequests.getName(), correctionrequestsTest.getName());
        Assert.assertEquals(correctionRequests.getCreationDate(), correctionrequestsTest.getCreationDate());
        Assert.assertEquals(correctionRequests.getDueDate(), correctionrequestsTest.getDueDate());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(customerPath).path(customer.getId().toString())
                .path(correctionRequestsPath).path(correctionRequests.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(correctionRequests, MediaType.APPLICATION_JSON));

        correctionrequestsTest = (CorrectionRequestDTO) response.readEntity(CorrectionRequestDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(correctionRequests.getId(), correctionrequestsTest.getId());
    }

    @Test
    @InSequence(10)
    public void listCorrectionRequestsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customer = oraculo.get(0);

        Response response = target.path(customerPath)
                .path(customer.getId().toString())
                .path(correctionRequestsPath)
                .request().cookie(cookieSessionId).get();

        String correctionRequestsList = response.readEntity(String.class);
        List<CorrectionRequestDTO> correctionRequestsListTest = new ObjectMapper().readValue(correctionRequestsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, correctionRequestsListTest.size());
    }

    @Test
    @InSequence(11)
    public void getCorrectionRequestsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CorrectionRequestDTO correctionRequests = oraculoCorrectionRequests.get(0);
        CustomerDTO customer = oraculo.get(0);

        CorrectionRequestDTO correctionrequestsTest = target.path(customerPath)
                .path(customer.getId().toString()).path(correctionRequestsPath)
                .path(correctionRequests.getId().toString())
                .request().cookie(cookieSessionId).get(CorrectionRequestDTO.class);

        Assert.assertEquals(correctionRequests.getId(), correctionrequestsTest.getId());
        Assert.assertEquals(correctionRequests.getName(), correctionrequestsTest.getName());
        Assert.assertEquals(correctionRequests.getCreationDate(), correctionrequestsTest.getCreationDate());
        Assert.assertEquals(correctionRequests.getDueDate(), correctionrequestsTest.getDueDate());
    }

    @Test
    @InSequence(12)
    public void removeCorrectionRequestsTest() {
        Cookie cookieSessionId = login(username, password);

        CorrectionRequestDTO correctionRequests = oraculoCorrectionRequests.get(0);
        CustomerDTO customer = oraculo.get(0);

        Response response = target.path(customerPath).path(customer.getId().toString())
                .path(correctionRequestsPath).path(correctionRequests.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
