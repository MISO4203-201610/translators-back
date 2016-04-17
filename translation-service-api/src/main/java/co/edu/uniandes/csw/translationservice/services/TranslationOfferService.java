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
import co.edu.uniandes.csw.translationservice.api.ITranslationOfferLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.converters.TranslationRequestConverter;
import co.edu.uniandes.csw.translationservice.converters.TranslatorConverter;
import co.edu.uniandes.csw.translationservice.dtos.TranslationOfferDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
import co.edu.uniandes.csw.translationservice.converters.TranslationOfferConverter;
import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentTranslator;
import javax.servlet.http.HttpServletRequest;


/**
 * @generated
 */
@Path("/translationOffers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TranslationOfferService {

    @Inject
    private ITranslationOfferLogic translationOfferLogic;
    @Inject
    private ITranslatorLogic translatorLogic;
    @Context
    private HttpServletRequest req;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    
    private static final int MAX_EMAIL =40;
    

    /**
     * Obtiene la lista de los registros de TranslatorOfert.
     *
     * @return Colección de objetos de TranslatorOfertDTO cada uno con sus
     * respectivos Review
     * @generated
     */
    @GET
    public List<TranslationOfferDTO> getTranslationOffers() {
        Long id = getCurrentTranslator(req.getRemoteUser()).getId();
        return TranslationOfferConverter.listEntity2DTO(translatorLogic.getTranslator(id).getTranslationOffers());
    }

    /**
     * Obtiene los datos de una instancia de TranslatorOfert a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TranslatorOfertDTO con los datos del TranslatorOfert
     * consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public TranslationOfferDTO getTranslationOffer(@PathParam("id") Long id) {
        return TranslationOfferConverter.fullEntity2DTO(translationOfferLogic.getTranslationOffer(id));
    }

    /**
     * Se encarga de crear un TranslatorOfert en la base de datos.
     *
     * @param dto Objeto de TranslatorOfertDTO con los datos nuevos
     * @return Objeto de TranslatorOfertDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public TranslationOfferDTO createTranslationOffer(TranslationOfferDTO dto) {
        TranslationOfferEntity entity = TranslationOfferConverter.fullDTO2Entity(dto);
        entity.setTranslator(getCurrentTranslator(req.getRemoteUser()));
        entity.setTranslationRequest(TranslationRequestConverter.fullDTO2Entity(dto.getTranslationRequest()));
        
        String[] to = new String[MAX_EMAIL];
        to[0]= "ing.rojas.m@gmail.com";
        
        String subject = "New Translator Offer was created ";
       
        String body = "Hi Customer, a new TranslatorOffer has been created";
        
        //System.out.println("body: "+body);
        
        
        //System.out.println("to: "+to);
        for(String it:to){
            //System.out.println("tounit: "+it);
            
        }
        List<TranslatorDTO> list = TranslatorConverter.listEntity2DTO(translatorLogic.getTranslators());
        MailService.sendMailAdmin(list, subject, body);

        return TranslationOfferConverter.fullEntity2DTO(translationOfferLogic.createTranslationOffer(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de TranslatorOfertDTO con los nuevos datos.
     * @return Instancia de TranslatorOfertDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public TranslationOfferDTO updateTranslationOffer(@PathParam("id") Long id, TranslationOfferDTO dto) {
        TranslationOfferEntity entity = TranslationOfferConverter.fullDTO2Entity(dto);
        entity.setTranslationRequest(TranslationRequestConverter.fullDTO2Entity(dto.getTranslationRequest()));
        entity.setId(id);
        return TranslationOfferConverter.fullEntity2DTO(translationOfferLogic.updateTranslationOffer(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTranslationOffer(@PathParam("id") Long id) {
        translationOfferLogic.deleteTranslationOffer(id);
    }
}
