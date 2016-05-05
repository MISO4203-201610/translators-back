package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.ResumeDTO;
import co.edu.uniandes.csw.translationservice.entities.ResumeEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class ResumeConverter {

    private ResumeConverter() {
    }

    public static ResumeDTO refEntity2DTO(ResumeEntity entity) {
        if (entity != null) {
            ResumeDTO dto = new ResumeDTO();
            dto.setId(entity.getId());
            dto.setProfessionalProfile(entity.getProfessionalProfile());
            dto.setAchievements(entity.getAchievements());
            dto.setPersonalInformation(entity.getPersonalInformation());
                
            return dto;
        } else {
            return null;
        }
    }

    public static ResumeEntity refDTO2Entity(ResumeDTO dto) {
        if (dto != null) {
            ResumeEntity entity = new ResumeEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    private static ResumeDTO basicEntity2DTO(ResumeEntity entity) {
        if (entity != null) {
            ResumeDTO dto = new ResumeDTO();
            dto.setId(entity.getId());
            dto.setProfessionalProfile(entity.getProfessionalProfile());
            dto.setAchievements(entity.getAchievements());
            dto.setPersonalInformation(entity.getPersonalInformation());
            dto.setTranslator(TranslatorConverter.refEntity2DTO(entity.getTranslator()));

            return dto;
        } else {
            return null;
        }
    }

    private static ResumeEntity basicDTO2Entity(ResumeDTO dto) {
        if (dto != null) {
            ResumeEntity entity = new ResumeEntity();
            entity.setId(dto.getId());
            entity.setProfessionalProfile(dto.getProfessionalProfile());
            entity.setAchievements(dto.getAchievements());
            entity.setPersonalInformation(dto.getPersonalInformation());
            entity.setTranslator(TranslatorConverter.refDTO2Entity(dto.getTranslator()));

            return entity;
        } else {
            return null;
        }
    }

    public static ResumeDTO fullEntity2DTO(ResumeEntity entity) {
        if (entity != null) {
            ResumeDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    public static ResumeEntity fullDTO2Entity(ResumeDTO dto) {
        if (dto != null) {
            ResumeEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    public static List<ResumeDTO> listEntity2DTO(List<ResumeEntity> entities) {
        List<ResumeDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (ResumeEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<ResumeEntity> listDTO2Entity(List<ResumeDTO> dtos) {
        List<ResumeEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (ResumeDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static ResumeEntity childDTO2Entity(ResumeDTO dto, TranslatorEntity parent){
        ResumeEntity entity = basicDTO2Entity(dto);
        entity.setTranslator(parent);
        return entity;
    }

    public static List<ResumeEntity> childListDTO2Entity(List<ResumeDTO> dtos, TranslatorEntity parent) {
        List<ResumeEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (ResumeDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}