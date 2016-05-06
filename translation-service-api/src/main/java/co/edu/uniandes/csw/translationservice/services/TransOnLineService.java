/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.translationservice.api.ITransOnLineLogic;
import co.edu.uniandes.csw.translationservice.converters.TransOnlineConverter;
import co.edu.uniandes.csw.translationservice.dtos.TransOnLineDTO;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineEntity;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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

/**
 *
 * @author juan camilo cerquera <jc.cerquera10@uniandes.edu.co>
 */
@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransOnLineService {

    @Context
    private HttpServletRequest req;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @Inject private ITransOnLineLogic transOnLineLogic;
    @Context private HttpServletResponse response;
   

    @POST
    @StatusCreated
    public TransOnLineDTO createTransOnLine(TransOnLineDTO dto) {
        TransOnLineDTO res;
        TransOnLineEntity  ch;
        String name= "CU"+dto.getIdCustomer()+"CO"+dto.getIdTranslator();
        dto.setName(name);
        ch = transOnLineLogic.getTransOnLine(name);
        if (ch==null){
            ch = TransOnlineConverter.fullDTO2Entity(dto);
            ch = transOnLineLogic.createTransOnLine(ch);
        }
        res= TransOnlineConverter.basicEntity2DTO(ch);
        return res;

    }
    @GET
    @Path("{desc}")
    public TransOnLineDTO getTransOnLine(@PathParam("desc") String description){
        TransOnLineDTO res = new  TransOnLineDTO();
        TransOnLineEntity entity= transOnLineLogic.getTransOnLine(description);
        if(entity!=null){
            res = TransOnlineConverter.fullEntity2DTO(entity);
        }
        return res;
    }
    
    @GET
    public List<TransOnLineDTO> getTransOnLines() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", transOnLineLogic.countTransOnLines());
            return TransOnlineConverter.listEntity2DTO(transOnLineLogic.getTransOnLines(page, maxRecords));
        }
        return TransOnlineConverter.listEntity2DTO(transOnLineLogic.getTransOnLines());
    }
    
    @PUT
    @Path("{id: \\d+}")
    public TransOnLineDTO updateTransOnLine(@PathParam("id") Long id, TransOnLineDTO dto) {
        TransOnLineEntity entity = TransOnlineConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return TransOnlineConverter.fullEntity2DTO(transOnLineLogic.updateTransOnLine(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTransOnLine(@PathParam("id") Long id) {
        transOnLineLogic.deleteTransOnLine(id);
    }
    
}
