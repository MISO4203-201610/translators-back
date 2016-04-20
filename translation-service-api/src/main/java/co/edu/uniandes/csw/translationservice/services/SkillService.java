package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.converters.LanguageConverter;
import co.edu.uniandes.csw.translationservice.dtos.LanguageDTO;
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

@Path("skills")
public class SkillService {

    @Inject
    private ITranslatorLogic translatorLogic;
    @Context
    private HttpServletRequest req;

    @GET
    public List<LanguageDTO> getLanguages() {
        Long id = getCurrentTranslator(req.getRemoteUser()).getId();
        return LanguageConverter.listEntity2DTO(translatorLogic.listLanguages(id));
    }

    @POST
    @StatusCreated
    @Path("{id: \\d+}")
    public LanguageDTO createLanguage(@PathParam("id") Long languageId) {
        Long translatorId = getCurrentTranslator(req.getRemoteUser()).getId();
        return LanguageConverter.fullEntity2DTO(translatorLogic.addLanguages(translatorId, languageId));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteLanguage(@PathParam("id") Long languageId) {
        Long translatorId = getCurrentTranslator(req.getRemoteUser()).getId();
        translatorLogic.removeLanguages(translatorId, languageId);
    }
}
