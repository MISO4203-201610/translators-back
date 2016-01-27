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
import co.edu.uniandes.csw.translationservice.api.ILanguageLogic;
import co.edu.uniandes.csw.translationservice.dtos.LanguageDTO;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.converters.LanguageConverter;

/**
 * @generated
 */
@Path("/languages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LanguageService {

    @Inject private ILanguageLogic languageLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de LanguageDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<LanguageDTO> getLanguages() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", languageLogic.countLanguages());
            return LanguageConverter.listEntity2DTO(languageLogic.getLanguages(page, maxRecords));
        }
        return LanguageConverter.listEntity2DTO(languageLogic.getLanguages());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de LanguageDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public LanguageDTO getLanguage(@PathParam("id") Long id) {
        return LanguageConverter.fullEntity2DTO(languageLogic.getLanguage(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de LanguageDTO con los datos nuevos
     * @return Objeto de LanguageDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public LanguageDTO createLanguage(LanguageDTO dto) {
        LanguageEntity entity = LanguageConverter.fullDTO2Entity(dto);
        return LanguageConverter.fullEntity2DTO(languageLogic.createLanguage(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de LanguageDTO con los nuevos datos.
     * @return Instancia de LanguageDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public LanguageDTO updateLanguage(@PathParam("id") Long id, LanguageDTO dto) {
        LanguageEntity entity = LanguageConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return LanguageConverter.fullEntity2DTO(languageLogic.updateLanguage(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLanguage(@PathParam("id") Long id) {
        languageLogic.deleteLanguage(id);
    }

}
