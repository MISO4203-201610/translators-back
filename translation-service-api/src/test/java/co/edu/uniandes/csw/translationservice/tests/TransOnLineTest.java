package co.edu.uniandes.csw.translationservice.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.translationservice.dtos.CustomerDTO;
import co.edu.uniandes.csw.translationservice.dtos.TransOnLineDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import co.edu.uniandes.csw.translationservice.services.TransOnLineService;
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

//@RunWith(Arquillian.class)
public class TransOnLineTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String chatNamePath = "chat";
    private final static List<TransOnLineDTO> oraculo = new ArrayList<>();
    private final static List<CustomerDTO> oraculoCustomers = new ArrayList<>();
    private final static List<TranslatorDTO> oraculoContractors = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");

    //@ArquillianResource
    private URL deploymentURL;

    //@Deployment(testable = false)
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
                .addPackage(TransOnLineService.class.getPackage())
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

    //@BeforeClass
    public static void setUp() {
        insertData();
    }

    public static void insertData() {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            
            TransOnLineDTO chatName = factory.manufacturePojo(TransOnLineDTO.class);
            chatName.setId(i + 1L);

            oraculo.add(chatName);
            
            CustomerDTO customers = factory.manufacturePojo(CustomerDTO.class);
            customers.setId(i + 1L);
            oraculoCustomers.add(customers);
            
            TranslatorDTO translator = factory.manufacturePojo(TranslatorDTO.class);
            translator.setId(i + 1L);
            oraculoContractors.add(translator);
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

    //@Before
    public void setUpTest() {
        target = createWebTarget();
    }

    //@Test
    //@InSequence(1)
    public void createTransOnLineTest() throws IOException {
        
        TranslatorDTO translator = oraculoContractors.get(0);
        CustomerDTO customer = oraculoCustomers.get(0);
        TransOnLineDTO transOnline = oraculo.get(0);
        transOnline.setIdTranslator(translator.getId());
        transOnline.setIdCustomer(customer.getId());
        
        Cookie cookieSessionId = login(username, password);
                     
        Response response = target.path("customers")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));
            
                Assert.assertEquals(Created, response.getStatus());
    
                response = target.path("translators")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(translator, MediaType.APPLICATION_JSON));
        
                Assert.assertEquals(Created, response.getStatus());
                
                response = target.path(chatNamePath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(transOnline, MediaType.APPLICATION_JSON));
                
                Assert.assertEquals(Created, response.getStatus());
        
        TransOnLineDTO  chatNameTest = (TransOnLineDTO) response.readEntity(TransOnLineDTO.class);
                
        Assert.assertEquals(transOnline.getId(), chatNameTest.getId());
        Assert.assertEquals(transOnline.getCreationDate(), chatNameTest.getCreationDate());
    }
    
    //@Test
    //@InSequence(2)
    public void getTransOnLineById() {
        Cookie cookieSessionId = login(username, password);
        TransOnLineDTO dto =oraculo.get(0);
        String name= "CU"+dto.getIdCustomer()+"CO"+dto.getIdTranslator();
        
        TransOnLineDTO chatNameTest = target.path(chatNamePath)
                .path(name)
                .request().cookie(cookieSessionId).get(TransOnLineDTO.class);
        chatNameTest.getName();
        Assert.assertEquals(chatNameTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(chatNameTest.getCreationDate(), oraculo.get(0).getCreationDate());
        Assert.assertEquals(chatNameTest.getName(), name);
    }
}
