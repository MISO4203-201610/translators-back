package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.converters.CorrectionRequestConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslationRequestConverter;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Path("catalog")
public class Catalog {

    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @Context
    private HttpServletResponse response;
    @Inject
    private ICorrectionRequestLogic correctionRequestLogic;
    @Inject
    private ITranslationRequestLogic translationRequestLogic;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de CorrectionRequestDTO cada uno con sus
     * respectivos Review
     * @generated
     */
    @GET
    @Path("corrections")
    public List<CorrectionRequestDTO> getCorrectionRequests() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", correctionRequestLogic.countCorrectionRequests());
            return CorrectionRequestConverter.listEntity2DTO(correctionRequestLogic.getCorrectionRequests(page, maxRecords));
        }
        return CorrectionRequestConverter.listEntity2DTO(correctionRequestLogic.getCorrectionRequests());
    }

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de TranslationRequestDTO cada uno con sus
     * respectivos Review
     * @generated
     */
    @GET
    @Path("translations")
    public List<TranslationRequestDTO> getTranslationRequests() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", translationRequestLogic.countTranslationRequests());
            return TranslationRequestConverter.listEntity2DTO(translationRequestLogic.getTranslationRequests());
        }
        return TranslationRequestConverter.listEntity2DTO(translationRequestLogic.getTranslationRequests());
    }
}
