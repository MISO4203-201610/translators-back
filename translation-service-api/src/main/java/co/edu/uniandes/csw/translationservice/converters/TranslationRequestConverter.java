package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.TranslationRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class TranslationRequestConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private TranslationRequestConverter() {
    }

    /**
     * Realiza la conversión de TranslationRequestEntity a TranslationRequestDTO.
     * Se invoca cuando otra entidad tiene una referencia a TranslationRequestEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de TranslationRequestEntity a convertir
     * @return instancia de TranslationRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationRequestDTO refEntity2DTO(TranslationRequestEntity entity) {
        if (entity != null) {
            TranslationRequestDTO dto = new TranslationRequestDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreationDate(entity.getCreationDate());
            dto.setDueDate(entity.getDueDate());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de TranslationRequestDTO a TranslationRequestEntity Se invoca cuando otro DTO
     * tiene una referencia a TranslationRequestDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de TranslationRequestDTO a convertir
     * @return instancia de TranslationRequestEntity con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationRequestEntity refDTO2Entity(TranslationRequestDTO dto) {
        if (dto != null) {
            TranslationRequestEntity entity = new TranslationRequestEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslationRequestEntity a TranslationRequestDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de TranslationRequestEntity a convertir
     * @return Instancia de TranslationRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    private static TranslationRequestDTO basicEntity2DTO(TranslationRequestEntity entity) {
        if (entity != null) {
            TranslationRequestDTO dto = new TranslationRequestDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreationDate(entity.getCreationDate());
            dto.setDueDate(entity.getDueDate());
            dto.setCustomer(CustomerConverter.refEntity2DTO(entity.getCustomer()));
            dto.setTargetLanguage(LanguageConverter.refEntity2DTO(entity.getTargetLanguage()));
            dto.setStatus(StatusConverter.refEntity2DTO(entity.getStatus()));
            dto.setOriginalLanguage(LanguageConverter.refEntity2DTO(entity.getOriginalLanguage()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslationRequestDTO a TranslationRequestEntity Se invoca cuando se
     * necesita convertir una instancia de TranslationRequestDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de TranslationRequestDTO a convertir
     * @return Instancia de TranslationRequestEntity creada a partir de los datos de dto
     * @generated
     */
    private static TranslationRequestEntity basicDTO2Entity(TranslationRequestDTO dto) {
        if (dto != null) {
            TranslationRequestEntity entity = new TranslationRequestEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setCreationDate(dto.getCreationDate());
            entity.setDueDate(dto.getDueDate());
            entity.setCustomer(CustomerConverter.refDTO2Entity(dto.getCustomer()));
            entity.setTargetLanguage(LanguageConverter.refDTO2Entity(dto.getTargetLanguage()));
            entity.setStatus(StatusConverter.refDTO2Entity(dto.getStatus()));
            entity.setOriginalLanguage(LanguageConverter.refDTO2Entity(dto.getOriginalLanguage()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de TranslationRequestEntity a TranslationRequestDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de TranslationRequestEntity a convertir
     * @return Instancia de TranslationRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationRequestDTO fullEntity2DTO(TranslationRequestEntity entity) {
        if (entity != null) {
            TranslationRequestDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslationRequestDTO a TranslationRequestEntity.
     * Incluye todos los atributos de TranslationRequestEntity.
     *
     * @param dto Instancia de TranslationRequestDTO a convertir
     * @return Instancia de TranslationRequestEntity con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationRequestEntity fullDTO2Entity(TranslationRequestDTO dto) {
        if (dto != null) {
            TranslationRequestEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de TranslationRequestEntity a TranslationRequestDTO. Para cada
     * instancia de TranslationRequestEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo TranslationRequestDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de TranslationRequestDTO
     * @generated
     */
    public static List<TranslationRequestDTO> listEntity2DTO(List<TranslationRequestEntity> entities) {
        List<TranslationRequestDTO> dtos = new ArrayList<TranslationRequestDTO>();
        if (entities != null) {
            for (TranslationRequestEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de TranslationRequestDTO a instancias de
     * TranslationRequestEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de TranslationRequestDTO a convertir
     * @return Collección de instancias de TranslationRequestEntity
     * @generated
     */
    public static List<TranslationRequestEntity> listDTO2Entity(List<TranslationRequestDTO> dtos) {
        List<TranslationRequestEntity> entities = new ArrayList<TranslationRequestEntity>();
        if (dtos != null) {
            for (TranslationRequestDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
