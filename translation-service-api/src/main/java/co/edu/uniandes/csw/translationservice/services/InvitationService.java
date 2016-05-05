package co.edu.uniandes.csw.translationservice.services;

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
import co.edu.uniandes.csw.translationservice.api.IInvitationLogic;
import co.edu.uniandes.csw.translationservice.dtos.InvitationDTO;
import co.edu.uniandes.csw.translationservice.entities.InvitationEntity;
import co.edu.uniandes.csw.translationservice.converters.InvitationConverter;

/**
 * @generated
 */
@Path("/invitations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvitationService {

    @Inject private IInvitationLogic invitationLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de InvitationDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<InvitationDTO> getInvitations() {
        this.response.setIntHeader("X-Total-Count", invitationLogic.countInvitations());
        return InvitationConverter.listEntity2DTO(invitationLogic.getInvitations());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de InvitationDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public InvitationDTO getInvitation(@PathParam("id") Long id) {
        return InvitationConverter.fullEntity2DTO(invitationLogic.getInvitation(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de InvitationDTO con los datos nuevos
     * @return Objeto de InvitationDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    public InvitationDTO createInvitation(InvitationDTO dto) {
        InvitationEntity entity = InvitationConverter.fullDTO2Entity(dto);
        return InvitationConverter.fullEntity2DTO(invitationLogic.createInvitation(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de InvitationDTO con los nuevos datos.
     * @return Instancia de InvitationDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public InvitationDTO updateInvitation(@PathParam("id") Long id, InvitationDTO dto) {
        InvitationEntity entity = InvitationConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return InvitationConverter.fullEntity2DTO(invitationLogic.updateInvitation(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteInvitation(@PathParam("id") Long id) {
        invitationLogic.deleteInvitation(id);
    }
}
