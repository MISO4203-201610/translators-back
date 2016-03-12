package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class TranslatorConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private TranslatorConverter() {
    }

    /**
     * Realiza la conversión de TranslatorEntity a TranslatorDTO.
     * Se invoca cuando otra entidad tiene una referencia a TranslatorEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de TranslatorEntity a convertir
     * @return instancia de TranslatorDTO con los datos recibidos por parámetro
     * @generated
     */
    public static TranslatorDTO refEntity2DTO(TranslatorEntity entity) {
        if (entity != null) {
            TranslatorDTO dto = new TranslatorDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPicture(entity.getPicture());
            dto.setBirthDate(entity.getBirthDate());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de TranslatorDTO a TranslatorEntity Se invoca cuando otro DTO
     * tiene una referencia a TranslatorDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de TranslatorDTO a convertir
     * @return instancia de TranslatorEntity con los datos recibidos por parámetro
     * @generated
     */
    public static TranslatorEntity refDTO2Entity(TranslatorDTO dto) {
        if (dto != null) {
            TranslatorEntity entity = new TranslatorEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslatorEntity a TranslatorDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de TranslatorEntity a convertir
     * @return Instancia de TranslatorDTO con los datos recibidos por parámetro
     * @generated
     */
    private static TranslatorDTO basicEntity2DTO(TranslatorEntity entity) {
        if (entity != null) {
            TranslatorDTO dto = new TranslatorDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPicture(entity.getPicture());
            dto.setBirthDate(entity.getBirthDate());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslatorDTO a TranslatorEntity Se invoca cuando se
     * necesita convertir una instancia de TranslatorDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de TranslatorDTO a convertir
     * @return Instancia de TranslatorEntity creada a partir de los datos de dto
     * @generated
     */
    private static TranslatorEntity basicDTO2Entity(TranslatorDTO dto) {
        if (dto != null) {
            TranslatorEntity entity = new TranslatorEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setPicture(dto.getPicture());
            entity.setBirthDate(dto.getBirthDate());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de TranslatorEntity a TranslatorDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de TranslatorEntity a convertir
     * @return Instancia de TranslatorDTO con los datos recibidos por parámetro
     * @generated
     */
    public static TranslatorDTO fullEntity2DTO(TranslatorEntity entity) {
        if (entity != null) {
            TranslatorDTO dto = basicEntity2DTO(entity);
            dto.setEducation(EducationConverter.listEntity2DTO(entity.getEducation()));
            dto.setLanguages(LanguageConverter.listEntity2DTO(entity.getLanguages()));
            dto.setKnowledgeAreas(KnowledgeAreaConverter.listEntity2DTO(entity.getKnowledgeAreas()));
            dto.setReviews(ReviewConverter.listEntity2DTO(entity.getReviews()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslatorDTO a TranslatorEntity.
     * Incluye todos los atributos de TranslatorEntity.
     *
     * @param dto Instancia de TranslatorDTO a convertir
     * @return Instancia de TranslatorEntity con los datos recibidos por parámetro
     * @generated
     */
    public static TranslatorEntity fullDTO2Entity(TranslatorDTO dto) {
        if (dto != null) {
            TranslatorEntity entity = basicDTO2Entity(dto);
            entity.setEducation(EducationConverter.childListDTO2Entity(dto.getEducation(), entity));
            entity.setLanguages(LanguageConverter.listDTO2Entity(dto.getLanguages()));
            entity.setKnowledgeAreas(KnowledgeAreaConverter.listDTO2Entity(dto.getKnowledgeAreas()));
            entity.setReviews(ReviewConverter.childListDTO2Entity(dto.getReviews(), entity));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de TranslatorEntity a TranslatorDTO. Para cada
     * instancia de TranslatorEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo TranslatorDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de TranslatorDTO
     * @generated
     */
    public static List<TranslatorDTO> listEntity2DTO(List<TranslatorEntity> entities) {
        List<TranslatorDTO> dtos = new ArrayList<TranslatorDTO>();
        if (entities != null) {
            for (TranslatorEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de TranslatorDTO a instancias de
     * TranslatorEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de TranslatorDTO a convertir
     * @return Collección de instancias de TranslatorEntity
     * @generated
     */
    public static List<TranslatorEntity> listDTO2Entity(List<TranslatorDTO> dtos) {
        List<TranslatorEntity> entities = new ArrayList<TranslatorEntity>();
        if (dtos != null) {
            for (TranslatorDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
