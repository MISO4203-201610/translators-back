package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
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
import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.converters.CorrectionRequestConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslatorConverter;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentCustomer;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/correctionRequests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CorrectionRequestService {

    @Inject
    private ICorrectionRequestLogic correctionRequestLogic;
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
     * @return Colección de objetos de CorrectionRequestDTO cada uno con sus
     * respectivos Review
     * @generated
     */
    @GET
    public List<CorrectionRequestDTO> getCorrectionRequests() {
        Long id = getCurrentCustomer(req.getRemoteUser()).getId();
        return CorrectionRequestConverter.listEntity2DTO(customerLogic.getCustomer(id).getCorrectionRequests());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CorrectionRequestDTO con los datos del Book
     * consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CorrectionRequestDTO getCorrectionRequest(@PathParam("id") Long id) {
        return CorrectionRequestConverter.fullEntity2DTO(correctionRequestLogic.getCorrectionRequest(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de CorrectionRequestDTO con los datos nuevos
     * @return Objeto de CorrectionRequestDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public CorrectionRequestDTO createCorrectionRequest(CorrectionRequestDTO dto) {
        CorrectionRequestEntity entity = CorrectionRequestConverter.fullDTO2Entity(dto);
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
        
        String subject = "New Correction Request has been created ";
        
        int words =dto.getNumberOfWords();
        
        String desc="none";
        String lang="none";

        if(dto.getDesctiption()!= null)
            desc = dto.getDesctiption();
        
        System.out.println("lang: "+lang);
        if(dto.getLanguage() != null)
        {
            lang = dto.getLanguage().getName();
        }
        
        String body = "Hi Traslator, a new Correction Request has been created with a total of: "+
               words+ " words. The languaje of this Requestis: "+lang+". The descrption upload for this request is: "+desc;

        String link = "http://localhost:9000/confirmCorrection";
        body += " To review the Request go to "+link;
        System.out.println("body: "+body);
        
        
        System.out.println("to: "+to);
        for(String it:to){
            System.out.println("tounit: "+it);
            
        }
        MailService.sendMailAdmin(to, subject, body);
        return CorrectionRequestConverter.fullEntity2DTO(correctionRequestLogic.createCorrectionRequest(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de CorrectionRequestDTO con los nuevos datos.
     * @return Instancia de CorrectionRequestDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CorrectionRequestDTO updateCorrectionRequest(@PathParam("id") Long id, CorrectionRequestDTO dto) {
        CorrectionRequestEntity entity = CorrectionRequestConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return CorrectionRequestConverter.fullEntity2DTO(correctionRequestLogic.updateCorrectionRequest(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCorrectionRequest(@PathParam("id") Long id) {
        correctionRequestLogic.deleteCorrectionRequest(id);
    }
}
