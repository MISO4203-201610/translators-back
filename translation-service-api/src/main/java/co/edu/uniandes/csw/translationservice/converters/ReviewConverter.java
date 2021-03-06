package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.ReviewDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.entities.ReviewEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class ReviewConverter {

    private ReviewConverter() {
    }

    /**
     * Convierte una instancia de ReviewEntity a ReviewDTO
     * Convierte todos los atributos propios de ReviewEntity
     * @param entity Instancia de ReviewEntity a convertir
     * @return 
     */
    public static ReviewDTO basicEntity2DTO(ReviewEntity entity) {
        if (entity != null) {

            ReviewDTO dto = new ReviewDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSource(entity.getSource());
            dto.setDescription(entity.getDescription());
            dto.setValue(entity.getValue());
            dto.setTranslator(TranslatorConverter.refEntity2DTO(entity.getTranslator()));
            dto.setCustomer(CustomerConverter.refEntity2DTO(entity.getCustomer()));

            return dto;
        }
        return null;
    }

    /**
     * Convierte una instancia de ReviewDTO a ReviewEntity
     * Convierte todos los atributos propios de ReviewDTO
     * @param dto
     * @return 
     */
    public static ReviewEntity basicDTO2Entity(ReviewDTO dto) {
        if (dto != null) {

            ReviewEntity entity = new ReviewEntity();

            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setSource(dto.getSource());
            entity.setDescription(dto.getDescription());
            entity.setValue(dto.getValue());
            entity.setTranslator(TranslatorConverter.refDTO2Entity(dto.getTranslator()));
            entity.setCustomer(CustomerConverter.refDTO2Entity(dto.getCustomer()));

            return entity;
        }
        return null;
    }

    /**
     * Convierte una colección de ReviewEntity a ReviewDTO
     * Por cada instancia de ReviewEntity, se ejecuta basicEntity2DTO y se guarda
     * el resultado en una nueva colección
     * @param entities Colección de instancias de ReviewEntity
     * @return Colección de instancias de ReviewDTO
     */
    public static List<ReviewDTO> listEntity2DTO(List<ReviewEntity> entities) {
        List<ReviewDTO> dtos = new ArrayList<ReviewDTO>();
        if (entities != null) {
            for (ReviewEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una coleccuón de ReviewDTO a ReviewEntity
     * Por cada instancia de ReviewDTO se ejecuta basicDTO2Entity y se guarda
     * el resultado en una nueva colección
     * @param dtos Colección de instancias de ReviewDTO
     * @return Colección de instancias de ReviewEntity
     */
    public static List<ReviewEntity> listDTO2Entity(List<ReviewDTO> dtos) {
        List<ReviewEntity> entities = new ArrayList<ReviewEntity>();
        if (dtos != null) {
            for (ReviewDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * Convierte una instancia de ReviewDTO a ReviewEntity asignando un valor
     * al atributo Translator de ReviewEntity. Se usa cuando se necesita convertir 
     * un ReviewDTO asignando el libro asociado
     * @param dto Instancia de ReviewDTO
     * @param parent Instancia de TranslatorEntity
     * @return Instancia de ReviewEntity con TranslatorEntity asociado
     */
    public static ReviewEntity childDTO2Entity(ReviewDTO dto, TranslatorEntity parent) {
        ReviewEntity entity = basicDTO2Entity(dto);
        entity.setTranslator(parent);
        return entity;
    }

    /**
     * Convierte una colección de instancias de ReviewDTO a ReviewEntity 
     * asignando el mismo padre para todos. Se usa cuando se necesita crear o 
     * actualizar varios ReviewEntity con el mismo Translator
     * @param dtos Colección de instancias de ReviewDTO
     * @param parent Instancia de TranslatorEntity
     * @return Colección de ReviewEntity con el atributo Translator asignado
     */
    public static List<ReviewEntity> childListDTO2Entity(List<ReviewDTO> dtos, TranslatorEntity parent) {
        List<ReviewEntity> entities = new ArrayList<ReviewEntity>();
        if (dtos != null) {
            for (ReviewDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
