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
import co.edu.uniandes.csw.translationservice.api.IKnowledgeAreaLogic;
import co.edu.uniandes.csw.translationservice.dtos.KnowledgeAreaDTO;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.converters.KnowledgeAreaConverter;

/**
 * @generated
 */
@Path("/knowledgeAreas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KnowledgeAreaService {

    @Inject private IKnowledgeAreaLogic knowledgeAreaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Knowledge Areas.
     *
     * @return Colección de objetos de KnowledgeAreaDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<KnowledgeAreaDTO> getKnowledgeAreas() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", knowledgeAreaLogic.countKnowledgeAreas());
            return KnowledgeAreaConverter.listEntity2DTO(knowledgeAreaLogic.getKnowledgeAreas(page, maxRecords));
        }
        return KnowledgeAreaConverter.listEntity2DTO(knowledgeAreaLogic.getKnowledgeAreas());
    }

    /**
     * Obtiene los datos de una instancia de Knowledge Areas a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de KnowledgeAreaDTO con los datos del Knowledge Areas consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public KnowledgeAreaDTO getKnowledgeArea(@PathParam("id") Long id) {
        return KnowledgeAreaConverter.fullEntity2DTO(knowledgeAreaLogic.getKnowledgeArea(id));
    }

    /**
     * Se encarga de crear un Knowledge Areas en la base de datos.
     *
     * @param dto Objeto de KnowledgeAreaDTO con los datos nuevos
     * @return Objeto de KnowledgeAreaDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public KnowledgeAreaDTO createKnowledgeArea(KnowledgeAreaDTO dto) {
        KnowledgeAreaEntity entity = KnowledgeAreaConverter.fullDTO2Entity(dto);
        return KnowledgeAreaConverter.fullEntity2DTO(knowledgeAreaLogic.createKnowledgeArea(entity));
    }

    /**
     * Actualiza la información de una instancia de Knowledge Areas.
     *
     * @param id Identificador de la instancia de Knowledge Areas a modificar
     * @param dto Instancia de KnowledgeAreaDTO con los nuevos datos.
     * @return Instancia de KnowledgeAreaDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public KnowledgeAreaDTO updateKnowledgeArea(@PathParam("id") Long id, KnowledgeAreaDTO dto) {
        KnowledgeAreaEntity entity = KnowledgeAreaConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return KnowledgeAreaConverter.fullEntity2DTO(knowledgeAreaLogic.updateKnowledgeArea(entity));
    }

    /**
     * Elimina una instancia de Knowledge Areas de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteKnowledgeArea(@PathParam("id") Long id) {
        knowledgeAreaLogic.deleteKnowledgeArea(id);
    }
}
