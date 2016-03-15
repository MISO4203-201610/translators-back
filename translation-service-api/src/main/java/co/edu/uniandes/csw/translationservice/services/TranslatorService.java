package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import static co.edu.uniandes.csw.auth.stormpath.Utils.getClient;
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
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.converters.TranslatorConverter;
import co.edu.uniandes.csw.translationservice.dtos.LanguageDTO;
import co.edu.uniandes.csw.translationservice.converters.LanguageConverter;
import co.edu.uniandes.csw.translationservice.dtos.KnowledgeAreaDTO;
import co.edu.uniandes.csw.translationservice.converters.KnowledgeAreaConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslatorOfertConverter;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorOfertDTO;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/translators")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TranslatorService {
    private static final String TRANSLATOR_GROUP_HREF = "https://api.stormpath.com/v1/groups/6rX3H3p1Ig7nAJOQDvBHDd";
    private static final String ADMIN_HREF = "https://api.stormpath.com/v1/groups/67wGeCpvQ2RfK2vMXvzFTw";    

    @Inject private ITranslatorLogic translatorLogic;
    @Context private HttpServletRequest req;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de TranslatorDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<TranslatorDTO> getTranslators() {
        String accountHref = req.getRemoteUser();
        
        if (accountHref == null)
            return new ArrayList<TranslatorDTO>();
        
        Account account = getClient().getResource(accountHref, Account.class);
        for (Group gr : account.getGroups()) {
            switch (gr.getHref()) {                    
                case ADMIN_HREF:
                    if (page != null && maxRecords != null) {
                        this.response.setIntHeader("X-Total-Count", translatorLogic.countTranslators());
                        return TranslatorConverter.listEntity2DTO(translatorLogic.getTranslators(page, maxRecords));
                    }
                    return TranslatorConverter.listEntity2DTO(translatorLogic.getTranslators());
                case TRANSLATOR_GROUP_HREF:
                    Integer id = (int) account.getCustomData().get("translatorId");
                    List<TranslatorDTO> list = new ArrayList();
                    list.add(TranslatorConverter.fullEntity2DTO(translatorLogic.getTranslator(id.longValue())));
                    return list;
                default:
                    return new ArrayList<TranslatorDTO>();
            }
        }
        
        // Nothing?
        return new ArrayList<TranslatorDTO>();
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TranslatorDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public TranslatorDTO getTranslator(@PathParam("id") Long id) {
        return TranslatorConverter.fullEntity2DTO(translatorLogic.getTranslator(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de TranslatorDTO con los datos nuevos
     * @return Objeto de TranslatorDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public TranslatorDTO createTranslator(TranslatorDTO dto) {
        TranslatorEntity entity = TranslatorConverter.fullDTO2Entity(dto);
        return TranslatorConverter.fullEntity2DTO(translatorLogic.createTranslator(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de TranslatorDTO con los nuevos datos.
     * @return Instancia de TranslatorDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public TranslatorDTO updateTranslator(@PathParam("id") Long id, TranslatorDTO dto) {
        TranslatorEntity entity = TranslatorConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return TranslatorConverter.fullEntity2DTO(translatorLogic.updateTranslator(entity));
    }

    /**
     * Elimina una instancia de Translator de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTranslator(@PathParam("id") Long id) {
        translatorLogic.deleteTranslator(id);
    }

    /**
     * Obtiene una colección de instancias de LanguageDTO asociadas a una
     * instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @return Colección de instancias de LanguageDTO asociadas a la instancia de Translator
     * @generated
     */
    @GET
    @Path("{translatorId: \\d+}/languages")
    public List<LanguageDTO> listLanguages(@PathParam("translatorId") Long translatorId) {
        return LanguageConverter.listEntity2DTO(translatorLogic.listLanguages(translatorId));
    }

    /**
     * Obtiene una instancia de Language asociada a una instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param languageId Identificador de la instancia de Language
     * @generated
     */
    @GET
    @Path("{translatorId: \\d+}/languages/{languageId: \\d+}")
    public LanguageDTO getLanguages(@PathParam("translatorId") Long translatorId, @PathParam("languageId") Long languageId) {
        return LanguageConverter.fullEntity2DTO(translatorLogic.getLanguages(translatorId, languageId));
    }

    /**
     * Asocia un Language existente a un Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param languageId Identificador de la instancia de Language
     * @return Instancia de LanguageDTO que fue asociada a Translator
     * @generated
     */
    @POST
    @Path("{translatorId: \\d+}/languages/{languageId: \\d+}")
    public LanguageDTO addLanguages(@PathParam("translatorId") Long translatorId, @PathParam("languageId") Long languageId) {
        return LanguageConverter.fullEntity2DTO(translatorLogic.addLanguages(translatorId, languageId));
    }

    /**
     * Remplaza las instancias de Language asociadas a una instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param languages Colección de instancias de LanguageDTO a asociar a instancia de Translator
     * @return Nueva colección de LanguageDTO asociada a la instancia de Translator
     * @generated
     */
    @PUT
    @Path("{translatorId: \\d+}/languages")
    public List<LanguageDTO> replaceLanguages(@PathParam("translatorId") Long translatorId, List<LanguageDTO> languages) {
        return LanguageConverter.listEntity2DTO(translatorLogic.replaceLanguages(translatorId, LanguageConverter.listDTO2Entity(languages)));
    }

    /**
     * Desasocia un Language existente de un Translator existente
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param languageId Identificador de la instancia de Language
     * @generated
     */
    @DELETE
    @Path("{translatorId: \\d+}/languages/{languageId: \\d+}")
    public void removeLanguages(@PathParam("translatorId") Long translatorId, @PathParam("languageId") Long languageId) {
        translatorLogic.removeLanguages(translatorId, languageId);
    }
    
    /**
     * Obtiene una colección de instancias de knowledgeAreaDTO asociadas a una
     * instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @return Colección de instancias de knowledgeAreaDTO asociadas a la instancia de Translator
     * @generated
     */
    @GET
    @Path("{translatorId: \\d+}/knowledgeAreas")
    public List<KnowledgeAreaDTO> listKnowledgeAreas(@PathParam("translatorId") Long translatorId) {
        return KnowledgeAreaConverter.listEntity2DTO(translatorLogic.listKnowledgeAreas(translatorId));
    }

    /**
     * Obtiene una instancia de knowledgeArea asociada a una instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param knowledgeAreaId Identificador de la instancia de knowledgeArea
     * @generated
     */
    @GET
    @Path("{translatorId: \\d+}/knowledgeAreas/{knowledgeAreaId: \\d+}")
    public KnowledgeAreaDTO getknowledgeAreas(@PathParam("translatorId") Long translatorId, @PathParam("knowledgeAreaId") Long knowledgeAreaId) {
        return KnowledgeAreaConverter.fullEntity2DTO(translatorLogic.getKnowledgeAreas(translatorId, knowledgeAreaId));
    }

    /**
     * Asocia un knowledgeArea existente a un Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param knowledgeAreaId Identificador de la instancia de knowledgeArea
     * @return Instancia de knowledgeAreaDTO que fue asociada a Translator
     * @generated
     */
    @POST
    @Path("{translatorId: \\d+}/knowledgeAreas/{knowledgeAreaId: \\d+}")
    public KnowledgeAreaDTO addknowledgeAreas(@PathParam("translatorId") Long translatorId, @PathParam("knowledgeAreaId") Long knowledgeAreaId) {
        return KnowledgeAreaConverter.fullEntity2DTO(translatorLogic.addKnowledgeAreas(translatorId, knowledgeAreaId));
    }

    /**
     * Remplaza las instancias de knowledgeArea asociadas a una instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param knowledgeAreas Colección de instancias de knowledgeAreaDTO a asociar a instancia de Translator
     * @return Nueva colección de knowledgeAreaDTO asociada a la instancia de Translator
     * @generated
     */
    @PUT
    @Path("{translatorId: \\d+}/knowledgeAreas")
    public List<KnowledgeAreaDTO> replaceKnowledgeAreas(@PathParam("translatorId") Long translatorId, List<KnowledgeAreaDTO> knowledgeAreas) {
        return KnowledgeAreaConverter.listEntity2DTO(translatorLogic.replaceKnowledgeAreas(translatorId, KnowledgeAreaConverter.listDTO2Entity(knowledgeAreas)));
    }

    /**
     * Desasocia un knowledgeArea existente de un Translator existente
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param knowledgeAreaId Identificador de la instancia de knowledgeArea
     * @generated
     */
    @DELETE
    @Path("{translatorId: \\d+}/knowledgeAreas/{knowledgeAreaId: \\d+}")
    public void removeknowledgeAreas(@PathParam("translatorId") Long translatorId, @PathParam("knowledgeAreaId") Long knowledgeAreaId) {
        translatorLogic.removeKnowledgeAreas(translatorId, knowledgeAreaId);
    }
    
    /**
     * Obtiene una colección de instancias de translatorOfertDTO asociadas a una
     * instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @return Colección de instancias de translatorOfertDTO asociadas a la instancia de Translator
     * @generated
     */
    @GET
    @Path("{translatorId: \\d+}/translatorOferts")
    public List<TranslatorOfertDTO> listTranslatorOferts(@PathParam("translatorId") Long translatorId) {
        return TranslatorOfertConverter.listEntity2DTO(translatorLogic.listTranslatorOferts(translatorId));
    }

    /**
     * Obtiene una instancia de translatorOfert asociada a una instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param translatorOfertId Identificador de la instancia de translatorOfert
     * @generated
     */
    @GET
    @Path("{translatorId: \\d+}/translatorOferts/{translatorOfertId: \\d+}")
    public TranslatorOfertDTO getTranslatorOferts(@PathParam("translatorId") Long translatorId, @PathParam("translatorOfertId") Long translatorOfertId) {
        return TranslatorOfertConverter.fullEntity2DTO(translatorLogic.getTranslatorOferts(translatorId, translatorOfertId));
    }

    /**
     * Asocia un translatorOfert existente a un Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param translatorOfertId Identificador de la instancia de translatorOfert
     * @return Instancia de translatorOfertDTO que fue asociada a Translator
     * @generated
     */
    @POST
    @Path("{translatorId: \\d+}/translatorOferts/{translatorOfertId: \\d+}")
    public TranslatorOfertDTO addTranslatorOferts(@PathParam("translatorId") Long translatorId, @PathParam("translatorOfertId") Long translatorOfertId) {
        return TranslatorOfertConverter.fullEntity2DTO(translatorLogic.addTranslatorOferts(translatorId, translatorOfertId));
    }

    /**
     * Remplaza las instancias de translatorOfert asociadas a una instancia de Translator
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param translatorOferts Colección de instancias de translatorOfertDTO a asociar a instancia de Translator
     * @return Nueva colección de translatorOfertDTO asociada a la instancia de Translator
     * @generated
     */
    @PUT
    @Path("{translatorId: \\d+}/translatorOferts")
    public List<TranslatorOfertDTO> replaceTranslatorOferts(@PathParam("translatorId") Long translatorId, List<TranslatorOfertDTO> translatorOferts) {
        return TranslatorOfertConverter.listEntity2DTO(translatorLogic.replaceTranslatorOferts(translatorId, TranslatorOfertConverter.listDTO2Entity(translatorOferts)));
    }

    /**
     * Desasocia un translatorOfert existente de un Translator existente
     *
     * @param translatorId Identificador de la instancia de Translator
     * @param translatorOfertId Identificador de la instancia de translatorOfert
     * @generated
     */
    @DELETE
    @Path("{translatorId: \\d+}/translatorOferts/{translatorOfertId: \\d+}")
    public void removeTranslatorOferts(@PathParam("translatorId") Long translatorId, @PathParam("translatorOfertId") Long translatorOfertId) {
        translatorLogic.removeTranslatorOferts(translatorId, translatorOfertId);
    }
}
