/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.TransOnLineDTO;
import co.edu.uniandes.csw.translationservice.dtos.TransOnLineMsgDTO;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineEntity;
import co.edu.uniandes.csw.translationservice.entities.TransOnLineMsgEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @authorjuan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
public abstract class TransOnlineConverter {
    
    private TransOnlineConverter(){
    }
    

    
    public static TransOnLineDTO basicEntity2DTO(TransOnLineEntity entity) {
        if (entity != null) {
            TransOnLineDTO dto = new TransOnLineDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreationDate(entity.getCreationDate());
            
            return dto;
        } else {
            return null;
        }
    }
    
    public static TransOnLineDTO fullEntity2DTO(TransOnLineEntity entity){
        TransOnLineDTO res= null;
        List<TransOnLineMsgDTO> listMsgDTO;
        List<TransOnLineMsgEntity> listMsgEntity;
        if (entity != null){
            res= basicEntity2DTO(entity);
            listMsgEntity = entity.getChatMsgs();
            listMsgDTO=TransOnLineMsgConverter.listEntity2DTO(listMsgEntity);
            res.setListChatMsg(listMsgDTO);
        }
        return res;
            
        
    }

    private static TransOnLineEntity basicDTO2Entity(TransOnLineDTO dto) {
        if (dto != null) {
            TransOnLineEntity entity = new TransOnLineEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            
            entity.setCreationDate(dto.getCreationDate());
            
            return entity;
        } else {
            return null;
        }
    }

    
    public static TransOnLineEntity fullDTO2Entity(TransOnLineDTO dto) {
        if (dto != null) {
            TransOnLineEntity entity = basicDTO2Entity(dto);
            TranslatorEntity con = new TranslatorEntity();
            con.setId(dto.getIdTranslator());            
            entity.setContractor(con);
            CustomerEntity cus = new CustomerEntity();
            cus.setId(dto.getIdCustomer());
            entity.setCustomer(cus);
            return entity;
        } else {
            return null;
        }
    }

    public static List<TransOnLineDTO> listEntity2DTO(List<TransOnLineEntity> entities) {
          List<TransOnLineDTO> dtos = new ArrayList<TransOnLineDTO>();
        if (entities != null) {
            for (TransOnLineEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
    
    
}
