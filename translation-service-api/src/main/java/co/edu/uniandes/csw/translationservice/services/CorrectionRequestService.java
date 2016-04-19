package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.converters.CorrectionRequestConverter;
import co.edu.uniandes.csw.translationservice.converters.KnowledgeAreaConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslatorConverter;
import co.edu.uniandes.csw.translationservice.dtos.KnowledgeAreaDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentCustomer;
import java.util.ArrayList;
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
        CustomerEntity customer = customerLogic.getCustomer(id);
        id = customer.getId();
        List<CorrectionRequestEntity> corrections = customer.getCorrectionRequests();
        return CorrectionRequestConverter.listEntity2DTO(corrections);
    }

    /**
     * Obtiene los datos de una instancia de CorrectionRequest a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CorrectionRequestDTO con los datos de la
     * instancia
     */
    @GET
    @Path("{id: \\d+}")
    public CorrectionRequestDTO getCorrectionRequest(@PathParam("id") Long id) {
        return CorrectionRequestConverter.fullEntity2DTO(correctionRequestLogic.getCorrectionRequest(id));
    }
    
    /**
     * Obtiene los traductores a recomendar para determinado correction request
     *
     * @param id Identificador de la instancia a recomendar
     * @param translatorId
     */
    @GET
    @Path("recommendations/{id: \\d+}/invite/{translatorId: \\d+}")
    public void sendInvitationCorrectionRequest(@PathParam("id") Long id, @PathParam("translatorId") Long translatorId) {
        
        // Get the actual request!
        CorrectionRequestDTO correctionRequest = CorrectionRequestConverter.fullEntity2DTO(correctionRequestLogic.getCorrectionRequest(id));
        
        // Email them
        List<TranslatorDTO> translator = new ArrayList<TranslatorDTO>();
        translator.add(TranslatorConverter.refEntity2DTO(translatorLogic.getTranslator(translatorId)));
        
        // Invite them
        String subject = "You've got an invitation";
        String body = "You've being invited to quote a correction request called " + correctionRequest.getName() + ". To give a quote go to: http://localhost:9000/#/confirmCorrection";
        MailService.sendMailAdmin(translator, subject, body);
    }
    
    /**
     * Obtiene los traductores a recomendar para determinado correction request
     *
     * @param id Identificador de la instancia a recomendar
     * @return Colección de TranslatorDTO con los datos de los Translator
     * a recomendar
     */
    @GET
    @Path("recommendations/{id: \\d+}")
    public List<TranslatorDTO> getRecommendationsCorrectionRequest(@PathParam("id") Long id) {
        
        // Filtrar las recomendaciones
        return CorrectionRequestConverter.fullEntity2RecommendationDTO(
            correctionRequestLogic.getCorrectionRequest(id),
            translatorLogic.getTranslators()
        );
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
        
        List<TranslatorDTO> list = TranslatorConverter.listEntity2DTO(translatorLogic.getTranslators());
        
        String subject = "New Correction Request has been created ";
        
        int words =dto.getNumberOfWords();
        
        String desc="none";
        String lang="none";

        if(dto.getDesctiption()!= null)
            desc = dto.getDesctiption();
        
        if(dto.getLanguage() != null)
        {
            lang = dto.getLanguage().getName();
        }
        
        String body = "Hi Traslator, a new Correction Request has been created with a total of: "+
               words+ " words. The languaje of this Requestis: "+lang+". The descrption upload for this request is: "+desc;

        String link = "http://localhost:9000/confirmCorrection";
        body += " To review the Request go to "+link;
        
        MailService.sendMailAdmin(list, subject, body);
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
    
        @GET
    @Path("{id: \\d+}/knowledges")
    public List<KnowledgeAreaDTO> listKnowledgeAreas(@PathParam("id") Long id) {
        return KnowledgeAreaConverter.listEntity2DTO(correctionRequestLogic.listKnowledgeAreas(id));
    }

    @GET
    @Path("{id: \\d+}/knowledges/{knowledgeId: \\d+}")
    public KnowledgeAreaDTO getKnowledgeAreas(@PathParam("id") Long id, @PathParam("knowledgeId") Long knowledgeId) {
        return KnowledgeAreaConverter.fullEntity2DTO(correctionRequestLogic.getKnowledgeAreas(id, knowledgeId));
    }

    @POST
    @Path("{id: \\d+}/knowledges/{knowledgeId: \\d+}")
    public KnowledgeAreaDTO addKnowledgeAreas(@PathParam("id") Long id, @PathParam("knowledgeId") Long knowledgeId) {
        return KnowledgeAreaConverter.fullEntity2DTO(correctionRequestLogic.addKnowledgeAreas(id, knowledgeId));
    }

    @PUT
    @Path("{id: \\d+}/knowledges")
    public List<KnowledgeAreaDTO> replaceKnowledgeAreas(@PathParam("id") Long id, List<KnowledgeAreaDTO> knowledges) {
        return KnowledgeAreaConverter.listEntity2DTO(correctionRequestLogic.replaceKnowledgeAreas(id, KnowledgeAreaConverter.listDTO2Entity(knowledges)));
    }

    @DELETE
    @Path("{id: \\d+}/knowledges/{knowledgeId: \\d+}")
    public void removeKnowledgeAreas(@PathParam("id") Long id, @PathParam("knowledgeId") Long knowledgeId) {
        correctionRequestLogic.removeKnowledgeAreas(id, knowledgeId);
    }
}
