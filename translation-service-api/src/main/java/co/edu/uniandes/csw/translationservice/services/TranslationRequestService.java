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
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.converters.TranslationRequestConverter;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentCustomer;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/translationRequests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TranslationRequestService {

    @Inject private ITranslationRequestLogic translationRequestLogic;
    @Context private HttpServletRequest req;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de TranslationRequestDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<TranslationRequestDTO> getTranslationRequests() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", translationRequestLogic.countTranslationRequests());
            return TranslationRequestConverter.listEntity2DTO(translationRequestLogic.getTranslationRequests(page, maxRecords));
        }
        return TranslationRequestConverter.listEntity2DTO(translationRequestLogic.getTranslationRequests());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TranslationRequestDTO con los datos del Book consultado y sus Review
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
