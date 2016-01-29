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
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import co.edu.uniandes.csw.translationservice.dtos.CustomerDTO;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.converters.CustomerConverter;
import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import co.edu.uniandes.csw.translationservice.converters.TranslationRequestConverter;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.converters.CorrectionRequestConverter;

/**
 * @generated
 */
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {

    @Inject private ICustomerLogic customerLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de CustomerDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<CustomerDTO> getCustomers() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", customerLogic.countCustomers());
            return CustomerConverter.listEntity2DTO(customerLogic.getCustomers(page, maxRecords));
        }
        return CustomerConverter.listEntity2DTO(customerLogic.getCustomers());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CustomerDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CustomerDTO getCustomer(@PathParam("id") Long id) {
        return CustomerConverter.fullEntity2DTO(customerLogic.getCustomer(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de CustomerDTO con los datos nuevos
     * @return Objeto de CustomerDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public CustomerDTO createCustomer(CustomerDTO dto) {
        CustomerEntity entity = CustomerConverter.fullDTO2Entity(dto);
        return CustomerConverter.fullEntity2DTO(customerLogic.createCustomer(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de CustomerDTO con los nuevos datos.
     * @return Instancia de CustomerDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CustomerDTO updateCustomer(@PathParam("id") Long id, CustomerDTO dto) {
        CustomerEntity entity = CustomerConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return CustomerConverter.fullEntity2DTO(customerLogic.updateCustomer(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCustomer(@PathParam("id") Long id) {
        customerLogic.deleteCustomer(id);
    }

    /**
     * Obtiene una colección de instancias de TranslationRequestDTO asociadas a una
     * instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @return Colección de instancias de TranslationRequestDTO asociadas a la instancia de Customer
     * @generated
     */
    @GET
    @Path("{customerId: \\d+}/translationRequests")
    public List<TranslationRequestDTO> listTranslationRequests(@PathParam("customerId") Long customerId) {
        return TranslationRequestConverter.listEntity2DTO(customerLogic.listTranslationRequests(customerId));
    }

    /**
     * Obtiene una instancia de TranslationRequest asociada a una instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param translationRequestId Identificador de la instancia de TranslationRequest
     * @generated
     */
    @GET
    @Path("{customerId: \\d+}/translationRequests/{translationRequestId: \\d+}")
    public TranslationRequestDTO getTranslationRequests(@PathParam("customerId") Long customerId, @PathParam("translationRequestId") Long translationRequestId) {
        return TranslationRequestConverter.fullEntity2DTO(customerLogic.getTranslationRequests(customerId, translationRequestId));
    }

    /**
     * Asocia un TranslationRequest existente a un Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param translationRequestId Identificador de la instancia de TranslationRequest
     * @return Instancia de TranslationRequestDTO que fue asociada a Customer
     * @generated
     */
    @POST
    @Path("{customerId: \\d+}/translationRequests/{translationRequestId: \\d+}")
    public TranslationRequestDTO addTranslationRequests(@PathParam("customerId") Long customerId, @PathParam("translationRequestId") Long translationRequestId) {
        return TranslationRequestConverter.fullEntity2DTO(customerLogic.addTranslationRequests(customerId, translationRequestId));
    }

    /**
     * Remplaza las instancias de TranslationRequest asociadas a una instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param translationRequests Colección de instancias de TranslationRequestDTO a asociar a instancia de Customer
     * @return Nueva colección de TranslationRequestDTO asociada a la instancia de Customer
     * @generated
     */
    @PUT
    @Path("{customerId: \\d+}/translationRequests")
    public List<TranslationRequestDTO> replaceTranslationRequests(@PathParam("customerId") Long customerId, List<TranslationRequestDTO> translationRequests) {
        return TranslationRequestConverter.listEntity2DTO(customerLogic.replaceTranslationRequests(customerId, TranslationRequestConverter.listDTO2Entity(translationRequests)));
    }

    /**
     * Desasocia un TranslationRequest existente de un Customer existente
     *
     * @param customerId Identificador de la instancia de Customer
     * @param translationRequestId Identificador de la instancia de TranslationRequest
     * @generated
     */
    @DELETE
    @Path("{customerId: \\d+}/translationRequests/{translationRequestId: \\d+}")
    public void removeTranslationRequests(@PathParam("customerId") Long customerId, @PathParam("translationRequestId") Long translationRequestId) {
        customerLogic.removeTranslationRequests(customerId, translationRequestId);
    }

    /**
     * Obtiene una colección de instancias de CorrectionRequestDTO asociadas a una
     * instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @return Colección de instancias de CorrectionRequestDTO asociadas a la instancia de Customer
     * @generated
     */
    @GET
    @Path("{customerId: \\d+}/correctionRequests")
    public List<CorrectionRequestDTO> listCorrectionRequests(@PathParam("customerId") Long customerId) {
        return CorrectionRequestConverter.listEntity2DTO(customerLogic.listCorrectionRequests(customerId));
    }

    /**
     * Obtiene una instancia de CorrectionRequest asociada a una instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param correctionRequestId Identificador de la instancia de CorrectionRequest
     * @generated
     */
    @GET
    @Path("{customerId: \\d+}/correctionRequests/{correctionRequestId: \\d+}")
    public CorrectionRequestDTO getCorrectionRequests(@PathParam("customerId") Long customerId, @PathParam("correctionRequestId") Long correctionRequestId) {
        return CorrectionRequestConverter.fullEntity2DTO(customerLogic.getCorrectionRequests(customerId, correctionRequestId));
    }

    /**
     * Asocia un CorrectionRequest existente a un Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param correctionRequestId Identificador de la instancia de CorrectionRequest
     * @return Instancia de CorrectionRequestDTO que fue asociada a Customer
     * @generated
     */
    @POST
    @Path("{customerId: \\d+}/correctionRequests/{correctionRequestId: \\d+}")
    public CorrectionRequestDTO addCorrectionRequests(@PathParam("customerId") Long customerId, @PathParam("correctionRequestId") Long correctionRequestId) {
        return CorrectionRequestConverter.fullEntity2DTO(customerLogic.addCorrectionRequests(customerId, correctionRequestId));
    }

    /**
     * Remplaza las instancias de CorrectionRequest asociadas a una instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param correctionRequests Colección de instancias de CorrectionRequestDTO a asociar a instancia de Customer
     * @return Nueva colección de CorrectionRequestDTO asociada a la instancia de Customer
     * @generated
     */
    @PUT
    @Path("{customerId: \\d+}/correctionRequests")
    public List<CorrectionRequestDTO> replaceCorrectionRequests(@PathParam("customerId") Long customerId, List<CorrectionRequestDTO> correctionRequests) {
        return CorrectionRequestConverter.listEntity2DTO(customerLogic.replaceCorrectionRequests(customerId, CorrectionRequestConverter.listDTO2Entity(correctionRequests)));
    }

    /**
     * Desasocia un CorrectionRequest existente de un Customer existente
     *
     * @param customerId Identificador de la instancia de Customer
     * @param correctionRequestId Identificador de la instancia de CorrectionRequest
     * @generated
     */
    @DELETE
    @Path("{customerId: \\d+}/correctionRequests/{correctionRequestId: \\d+}")
    public void removeCorrectionRequests(@PathParam("customerId") Long customerId, @PathParam("correctionRequestId") Long correctionRequestId) {
        customerLogic.removeCorrectionRequests(customerId, correctionRequestId);
    }
}
