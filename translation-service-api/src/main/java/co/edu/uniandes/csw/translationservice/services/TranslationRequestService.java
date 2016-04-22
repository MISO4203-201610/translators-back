package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
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
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.converters.KnowledgeAreaConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslatorOfertConverter;
import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.converters.TranslationRequestConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslatorConverter;
import co.edu.uniandes.csw.translationservice.dtos.KnowledgeAreaDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorOfertDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentCustomer;
import java.util.ArrayList;
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
     * Obtiene los traductores a recomendar para determinado translation request
     *
     * @param id Identificador de la instancia a recomendar
     * @return Colección de TranslatorDTO con los datos de los Translator
     * a recomendar
     */
    @GET
    @Path("recommendations/{id: \\d+}/invite/{translatorId: \\d+}")
    public void sendInvitationTranslationRequest(@PathParam("id") Long id, @PathParam("translatorId") Long translatorId) {
        
        // Email them
        List<TranslatorDTO> translator = new ArrayList<TranslatorDTO>();
        translator.add(TranslatorConverter.refEntity2DTO(translatorLogic.getTranslator(translatorId)));
        
        // Invite them
        String subject = "You've got an invitation";
        String body = "You've being invited to quote a translation request. To give a quote go to: http://localhost:9000/#/confirmTranslation";
        MailService.sendMailAdmin(translator, subject, body);
    }
    
    /**
     * Obtiene los traductores a recomendar para determinado translation request
     *
     * @param id Identificador de la instancia a recomendar
     * @return Colección de TranslatorDTO con los datos de los Translator
     * a recomendar
     */
    @GET
    @Path("recommendations/{id: \\d+}")
    public List<TranslatorDTO> getRecommendationsTranslationRequest(@PathParam("id") Long id) {
        
        // Filtrar las recomendaciones
        List<TranslatorDTO> recommendations = TranslationRequestConverter.fullEntity2RecommendationDTO(
            translationRequestLogic.getTranslationRequest(id),
            translatorLogic.getTranslators()
        );
        
        // Retornar las recomendaciones
        return recommendations;
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
        
        List<TranslatorDTO> list = TranslatorConverter.listEntity2DTO(translatorLogic.getTranslators());
        
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
        
        MailService.sendMailAdmin(list, subject, body);

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
    

    @GET
    @Path("{id: \\d+}/knowledges")
    public List<KnowledgeAreaDTO> listKnowledgeAreas(@PathParam("id") Long id) {
        return KnowledgeAreaConverter.listEntity2DTO(translationRequestLogic.listKnowledgeAreas(id));
    }

    @GET
    @Path("{id: \\d+}/knowledges/{knowledgeId: \\d+}")
    public KnowledgeAreaDTO getKnowledgeAreas(@PathParam("id") Long id, @PathParam("knowledgeId") Long knowledgeId) {
        return KnowledgeAreaConverter.fullEntity2DTO(translationRequestLogic.getKnowledgeAreas(id, knowledgeId));
    }

    @POST
    @Path("{id: \\d+}/knowledges/{knowledgeId: \\d+}")
    public KnowledgeAreaDTO addKnowledgeAreas(@PathParam("id") Long id, @PathParam("knowledgeId") Long knowledgeId) {
        return KnowledgeAreaConverter.fullEntity2DTO(translationRequestLogic.addKnowledgeAreas(id, knowledgeId));
    }

    @PUT
    @Path("{id: \\d+}/knowledges")
    public List<KnowledgeAreaDTO> replaceKnowledgeAreas(@PathParam("id") Long id, List<KnowledgeAreaDTO> knowledges) {
        return KnowledgeAreaConverter.listEntity2DTO(translationRequestLogic.replaceKnowledgeAreas(id, KnowledgeAreaConverter.listDTO2Entity(knowledges)));
    }

    @DELETE
    @Path("{id: \\d+}/knowledges/{knowledgeId: \\d+}")
    public void removeKnowledgeAreas(@PathParam("id") Long id, @PathParam("knowledgeId") Long knowledgeId) {
        translationRequestLogic.removeKnowledgeAreas(id, knowledgeId);
    }
    
    @GET
    @Path("{id: \\d+}/translatorOferts")
    public List<TranslatorOfertDTO> listTranslatorOferts(@PathParam("id") Long id) {
        return TranslatorOfertConverter.listEntity2DTO(translationRequestLogic.listTranslatorOferts(id));
    }

    @GET
    @Path("{id: \\d+}/translatorOferts/{translatorOfertId: \\d+}")
    public TranslatorOfertDTO getTranslatorOferts(@PathParam("id") Long id, @PathParam("translatorOfertId") Long translatorOfertId) {
        return TranslatorOfertConverter.fullEntity2DTO(translationRequestLogic.getTranslatorOferts(id, translatorOfertId));
    }

    @POST
    @Path("{id: \\d+}/translatorOferts/{translatorOfertId: \\d+}")
    public TranslatorOfertDTO addTranslatorOferts(@PathParam("id") Long id, @PathParam("translatorOfertId") Long translatorOfertId) {
        return TranslatorOfertConverter.fullEntity2DTO(translationRequestLogic.addTranslatorOferts(id, translatorOfertId));
    }

    @PUT
    @Path("{id: \\d+}/translatorOferts")
    public List<TranslatorOfertDTO> replaceTranslatorOferts(@PathParam("id") Long id, List<TranslatorOfertDTO> translatorOferts) {
        return TranslatorOfertConverter.listEntity2DTO(translationRequestLogic.replaceTranslatorOferts(id, TranslatorOfertConverter.listDTO2Entity(translatorOferts)));
    }

    @DELETE
    @Path("{id: \\d+}/translatorOferts/{translatorOfertId: \\d+}")
    public void removeTranslatorOferts(@PathParam("id") Long id, @PathParam("translatorOfertId") Long translatorOfertId) {
        translationRequestLogic.removeTranslatorOferts(id, translatorOfertId);
    }
}
