/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.translationservice.api.ITransOnLineMsgLogic;
import co.edu.uniandes.csw.translationservice.converters.TransOnLineMsgConverter;
import co.edu.uniandes.csw.translationservice.dtos.TransOnLineMsgDTO;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineMsgEntity;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@Path("/chatmsg")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransOnLineMsgService {
    @Context
    private HttpServletRequest req;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @Inject private ITransOnLineMsgLogic transOnLineMsgLogic;
    
    
    
    @POST
    @StatusCreated
    public TransOnLineMsgDTO createTransOnLineMsg(TransOnLineMsgDTO dto) {
        TransOnLineMsgDTO res;
        
        TransOnLineMsgEntity  ch = TransOnLineMsgConverter.fullDTO2Entity(dto);
        ch = transOnLineMsgLogic.createTransOnLineMsg(ch);
        res= TransOnLineMsgConverter.basicEntity2DTO(ch);
        return res;

    }
    
}

