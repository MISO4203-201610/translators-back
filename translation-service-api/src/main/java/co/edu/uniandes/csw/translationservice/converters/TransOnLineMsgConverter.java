/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.TransOnLineMsgDTO;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineEntity;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineMsgEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.badillo10
 */
public abstract class TransOnLineMsgConverter {
    
    private TransOnLineMsgConverter(){
    
    }
    
    
    public static TransOnLineMsgDTO basicEntity2DTO(TransOnLineMsgEntity entity) {
        if (entity != null) {
            TransOnLineMsgDTO dto = new TransOnLineMsgDTO();
            dto.setIdChatName(entity.getChatName().getId());
            dto.setUserName(entity.getUserName());
            dto.setUserMsg(entity.getUserMesg());
           
            
            return dto;
        } else {
            return null;
        }
    }

    private static TransOnLineMsgEntity basicDTO2Entity(TransOnLineMsgDTO dto) {
        if (dto != null) {
            TransOnLineMsgEntity entity = new TransOnLineMsgEntity();
            entity.setUserName(dto.getUserName());
            entity.setUserMesg(dto.getUserMsg());
            
            return entity;
        } else {
            return null;
        }
    }

    
    public static TransOnLineMsgEntity fullDTO2Entity(TransOnLineMsgDTO dto) {
        if (dto != null) {
            TransOnLineMsgEntity entity = basicDTO2Entity(dto);
            TransOnLineEntity con = new TransOnLineEntity();
            con.setId(dto.getIdChatName() );            
            entity.setChatName(con);
            return entity;
        } else {
            return null;
        }
    }
    
    public static List<TransOnLineMsgEntity> listDTO2Entity(List<TransOnLineMsgDTO> dtos) {
        List<TransOnLineMsgEntity> entities = new ArrayList<TransOnLineMsgEntity>();
        if (dtos != null) {
            for (TransOnLineMsgDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static List<TransOnLineMsgDTO> listEntity2DTO(List<TransOnLineMsgEntity> entities){
        List<TransOnLineMsgDTO> dtos = new ArrayList<TransOnLineMsgDTO>();
        if (entities != null){
            for (TransOnLineMsgEntity entity : entities ){
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
    
}
