package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.converters.TranslationRequestConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslatorConverter;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentCustomer;
import javax.servlet.http.HttpServletRequest;


/**
 * @generated
 */
@Path("/translationRequests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TranslationRequestService {

    @Inject
    private ITranslationRequestLogic translationRequestLogic;
    @Inject
    private ICustomerLogic customerLogic;
    @Context
    private HttpServletRequest req;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    
    private static final int MAX_EMAIL =40;
    
    @Inject private ITranslatorLogic translatorLogic;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de TranslationRequestDTO cada uno con sus
     * respectivos Review
     * @generated
     */
    @GET
    public List<TranslationRequestDTO> getTranslationRequests() {
        Long id = getCurrentCustomer(req.getRemoteUser()).getId();
        return TranslationRequestConverter.listEntity2DTO(customerLogic.getCustomer(id).getTranslationRequests());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TranslationRequestDTO con los datos del Book
     * consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public TranslationRequestDTO getTranslationRequest(@PathParam("id") Long id) {
        return TranslationRequestConverter.fullEntity2DTO(translationRequestLogic.getTranslationRequest(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de TranslationRequestDTO con los datos nuevos
     * @return Objeto de TranslationRequestDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public TranslationRequestDTO createTranslationRequest(TranslationRequestDTO dto) {
        TranslationRequestEntity entity = TranslationRequestConverter.fullDTO2Entity(dto);
        entity.setCustomer(getCurrentCustomer(req.getRemoteUser()));
        
        String[] to= new String[MAX_EMAIL];
        to[0]= "jhonyt37@gmail.com";
        
        List<TranslatorDTO> list = TranslatorConverter.listEntity2DTO(translatorLogic.getTranslators());
        int i=1;
        if(!list.isEmpty()){
            for(TranslatorDTO item:list){
                System.out.println("item name: "+item.getName());
                System.out.println("item email: "+item.getEmail());
                if(item.getEmail()!=null)
                {
                to[i]=item.getEmail();
                i++;
                }
            }
            
        }
        
        String subject = "New Translation Request has been created ";
        
        int words =dto.getNumberOfWords();
        String origin="none";
        String target="none";
        
        if(dto.getOriginalLanguage()!=null)
          origin=dto.getOriginalLanguage().getName();
       
        if(dto.getTargetLanguage()!=null)
         target =dto.getTargetLanguage().getName();
       
        String body = "Hi Traslator, a new Translation Request has been created with a total of: "+
               words+ " words. This has a "+origin+" origin language and must be translated to "+target+" language. ";

        String link = "http://localhost:9000/cnfirmTranslate";
        body += "To review the Request go to "+link;
        System.out.println("body: "+body);
        
        
        System.out.println("to: "+to);
        for(String it:to){
            System.out.println("tounit: "+it);
            
        }
        MailService.sendMailAdmin(to, subject, body);

        return TranslationRequestConverter.fullEntity2DTO(translationRequestLogic.createTranslationRequest(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de TranslationRequestDTO con los nuevos datos.
     * @return Instancia de TranslationRequestDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public TranslationRequestDTO updateTranslationRequest(@PathParam("id") Long id, TranslationRequestDTO dto) {
        TranslationRequestEntity entity = TranslationRequestConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return TranslationRequestConverter.fullEntity2DTO(translationRequestLogic.updateTranslationRequest(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTranslationRequest(@PathParam("id") Long id) {
        translationRequestLogic.deleteTranslationRequest(id);
    }
}
