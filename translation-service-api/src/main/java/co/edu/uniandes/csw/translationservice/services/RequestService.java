package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import static co.edu.uniandes.csw.auth.stormpath.Utils.getClient;
import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import co.edu.uniandes.csw.translationservice.converters.CorrectionRequestConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslationRequestConverter;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.dtos.RequestDTO;
import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import static co.edu.uniandes.csw.translationservice.services.AccountService.CUSTOMER_CUSTOM_DATA_KEY;
import com.stormpath.sdk.account.Account;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/requests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestService {

    @Context
    private HttpServletRequest servletReq;

    @Inject
    private ITranslationRequestLogic translationReqLogic;

    @Inject
    private ICorrectionRequestLogic correctionReqLogic;

    @POST
    @StatusCreated
    public void createRequest(RequestDTO dto) {
        String accountHref = servletReq.getRemoteUser();
        if (accountHref == null) {
            throw new WebApplicationException(HttpServletResponse.SC_BAD_REQUEST);
        }
        Account account = getClient().getResource(accountHref, Account.class);
        Long customerId = (Long) account.getCustomData().get(CUSTOMER_CUSTOM_DATA_KEY);
        if (customerId != null) {
            CustomerEntity customer = new CustomerEntity();
            customer.setId(customerId);
            if (dto instanceof TranslationRequestDTO) {
                TranslationRequestEntity entity = TranslationRequestConverter.fullDTO2Entity((TranslationRequestDTO) dto);
                entity.setCustomer(customer);
                translationReqLogic.createTranslationRequest(entity);
            } else if (dto instanceof CorrectionRequestDTO) {
                CorrectionRequestEntity entity = CorrectionRequestConverter.fullDTO2Entity((CorrectionRequestDTO) dto);
                entity.setCustomer(customer);
                correctionReqLogic.createCorrectionRequest(entity);
            }
        }
    }
}
