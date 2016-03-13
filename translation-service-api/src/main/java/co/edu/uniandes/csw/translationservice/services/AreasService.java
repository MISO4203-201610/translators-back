package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.converters.KnowledgeAreaConverter;
import co.edu.uniandes.csw.translationservice.dtos.KnowledgeAreaDTO;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentTranslator;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

@Path("areas")
public class AreasService {

    @Inject
    private ITranslatorLogic translatorLogic;
    @Context
    private HttpServletRequest req;

    @GET
    public List<KnowledgeAreaDTO> getKnowledgeAreas() {
        Long id = getCurrentTranslator(req.getRemoteUser()).getId();
        return KnowledgeAreaConverter.listEntity2DTO(translatorLogic.listKnowledgeAreas(id));
    }

    @POST
    @StatusCreated
    @Path("{id: \\d+}")
    public KnowledgeAreaDTO createKnowledgeArea(@PathParam("id") Long knowledgeAreaId) {
        Long translatorId = getCurrentTranslator(req.getRemoteUser()).getId();
        return KnowledgeAreaConverter.fullEntity2DTO(translatorLogic.addKnowledgeAreas(translatorId, knowledgeAreaId));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteKnowledgeArea(@PathParam("id") Long knowledgeAreaId) {
        Long translatorId = getCurrentTranslator(req.getRemoteUser()).getId();
        translatorLogic.removeKnowledgeAreas(translatorId, knowledgeAreaId);
    }
}