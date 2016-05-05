/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.translationservice.api.IResumeLogic;
import co.edu.uniandes.csw.translationservice.converters.ResumeConverter;
import co.edu.uniandes.csw.translationservice.dtos.ResumeDTO;
import co.edu.uniandes.csw.translationservice.entities.ResumeEntity;
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

@Path("/resumes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResumeService {

    @Inject private IResumeLogic ResumeLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    @GET
    public List<ResumeDTO> getResumes() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", ResumeLogic.countResumes());
            return ResumeConverter.listEntity2DTO(ResumeLogic.getResumes(page, maxRecords));
        }
        return ResumeConverter.listEntity2DTO(ResumeLogic.getResumes());
    }

    @GET
    @Path("{id: \\d+}")
    public ResumeDTO getResume(@PathParam("id") Long id) {
        return ResumeConverter.fullEntity2DTO(ResumeLogic.getResume(id));
    }

    @POST
    @StatusCreated
    public ResumeDTO createResume(ResumeDTO dto) {
        ResumeEntity entity = ResumeConverter.fullDTO2Entity(dto);
        return ResumeConverter.fullEntity2DTO(ResumeLogic.createResume(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public ResumeDTO updateResume(@PathParam("id") Long id, ResumeDTO dto) {
        ResumeEntity entity = ResumeConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ResumeConverter.fullEntity2DTO(ResumeLogic.updateResume(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteResume(@PathParam("id") Long id) {
        ResumeLogic.deleteResume(id);
    }

    @GET
    @Path("{translatorId: \\d+}/translator")
    public List<ResumeDTO> getByFreelancer(@PathParam("translatorId") Long translatorId) {

        if (translatorId != null) {
            return ResumeConverter.listEntity2DTO(ResumeLogic.getByTranslator(translatorId));
        } else {
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", ResumeLogic.countResumes());
                return ResumeConverter.listEntity2DTO(ResumeLogic.getResumes(page, maxRecords));
            }
            return ResumeConverter.listEntity2DTO(ResumeLogic.getResumes());
        }
    }
}