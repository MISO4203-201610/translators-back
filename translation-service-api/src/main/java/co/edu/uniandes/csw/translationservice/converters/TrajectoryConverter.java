/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.converters;
   
import co.edu.uniandes.csw.translationservice.dtos.TrajectoryDTO;
import co.edu.uniandes.csw.translationservice.entities.TrajectoryEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class TrajectoryConverter {


    private TrajectoryConverter() {
    }
    
    public static TrajectoryDTO refEntity2DTO(TrajectoryEntity entity) {
        if (entity != null) {
            TrajectoryDTO dto = new TrajectoryDTO();
            
            dto.setId(entity.getId());
            dto.setProjectName(entity.getProjectName());
            dto.setDescription(entity.getDescription());
            dto.setDuration(entity.getDuration());
            dto.setCompany(entity.getCompany());
            
            return dto;
        } else {
            return null;
        }
    }

    public static TrajectoryEntity refDTO2Entity(TrajectoryDTO dto) {
        if (dto != null) {
           TrajectoryEntity entity = new TrajectoryEntity();
           entity.setId(dto.getId());
           
           return entity;
        } else {
            return null;
        }
    }

    private static TrajectoryDTO basicEntity2DTO(TrajectoryEntity entity) {
        if (entity != null) {
            TrajectoryDTO dto = new TrajectoryDTO();
            
            dto.setId(entity.getId());
            dto.setProjectName(entity.getProjectName());
            dto.setDescription(entity.getDescription());
            dto.setDuration(entity.getDuration());
            dto.setCompany(entity.getCompany());
            dto.setTranslator(TranslatorConverter.refEntity2DTO(entity.getTranslator()));

            return dto;
        } else {
            return null;
        }
    }

    private static TrajectoryEntity basicDTO2Entity(TrajectoryDTO dto) {
        if (dto != null) {
            TrajectoryEntity entity = new TrajectoryEntity();
            
            entity.setId(dto.getId());
            entity.setId(dto.getId());
            entity.setProjectName(dto.getProjectName());
            entity.setDescription(dto.getDescription());
            entity.setDuration(dto.getDuration());
            entity.setCompany(dto.getCompany());
            entity.setTranslator(TranslatorConverter.refDTO2Entity(dto.getTranslator()));

            return entity;
        } else {
            return null;
        }
    }

    public static TrajectoryDTO fullEntity2DTO(TrajectoryEntity entity) {
        if (entity != null) {
            TrajectoryDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    public static TrajectoryEntity fullDTO2Entity(TrajectoryDTO dto) {
        if (dto != null) {
            TrajectoryEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    public static List<TrajectoryDTO> listEntity2DTO(List<TrajectoryEntity> entities) {
        List<TrajectoryDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (TrajectoryEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<TrajectoryEntity> listDTO2Entity(List<TrajectoryDTO> dtos) {
        List<TrajectoryEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (TrajectoryDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static TrajectoryEntity childDTO2Entity(TrajectoryDTO dto, TranslatorEntity parent){
            TrajectoryEntity entity= basicDTO2Entity(dto);
            entity.setTranslator(parent);
            return entity;
    }
    
    public static List<TrajectoryEntity> childListDTO2Entity(List<TrajectoryDTO> dtos, TranslatorEntity parent) {
        List<TrajectoryEntity> entities = new ArrayList<TrajectoryEntity>();
        if (dtos != null) {
            for (TrajectoryDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
    
    
}

