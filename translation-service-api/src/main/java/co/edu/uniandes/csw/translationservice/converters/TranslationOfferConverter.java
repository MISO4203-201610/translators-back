package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.TranslationOfferDTO;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Germán
 */
public abstract class TranslationOfferConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private TranslationOfferConverter() {
    }

    /**
     * Realiza la conversión de TranslatorOfertEntity a TranslatorOfertDTO.
     * Se invoca cuando otra entidad tiene una referencia a TranslatorOfertEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de TranslatorOfertEntity a convertir
     * @return instancia de TranslatorOfertDTO con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationOfferDTO refEntity2DTO(TranslationOfferEntity entity) {
        if (entity != null) {
            TranslationOfferDTO dto = new TranslationOfferDTO();
            dto.setId(entity.getId());
            dto.setPrice(entity.getPrice());
            dto.setComment(entity.getComment());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de TranslatorOfertDTO a TranslatorOfertEntity Se invoca cuando otro DTO
     * tiene una referencia a TranslatorOfertDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de TranslatorOfertDTO a convertir
     * @return instancia de TranslatorOfertEntity con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationOfferEntity refDTO2Entity(TranslationOfferDTO dto) {
        if (dto != null) {
            TranslationOfferEntity entity = new TranslationOfferEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslatorOfertEntity a TranslatorOfertDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de TranslatorOfertEntity a convertir
     * @return Instancia de TranslatorOfertDTO con los datos recibidos por parámetro
     * @generated
     */
    private static TranslationOfferDTO basicEntity2DTO(TranslationOfferEntity entity) {
        if (entity != null) {
            TranslationOfferDTO dto = new TranslationOfferDTO();
            dto.setId(entity.getId());
            dto.setPrice(entity.getPrice());
            dto.setComment(entity.getComment());
            dto.setTranslationRequest(TranslationRequestConverter.refEntity2DTO(entity.getTranslationRequest()));
            dto.setTranslator(TranslatorConverter.refEntity2DTO(entity.getTranslator()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslatorDTO a TranslatorOfertEntity Se invoca cuando se
     * necesita convertir una instancia de TranslatorOfertDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de TranslatorOfertDTO a convertir
     * @return Instancia de TranslatorOfertEntity creada a partir de los datos de dto
     * @generated
     */
    private static TranslationOfferEntity basicDTO2Entity(TranslationOfferDTO dto) {
        if (dto != null) {
            TranslationOfferEntity entity = new TranslationOfferEntity();
            entity.setId(dto.getId());
            entity.setPrice(dto.getPrice());
            entity.setComment(dto.getComment());
            
            entity.setTranslationRequest(TranslationRequestConverter.refDTO2Entity(dto.getTranslationRequest()));
            entity.setTranslator(TranslatorConverter.refDTO2Entity(dto.getTranslator()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de TranslatorOfertEntity a TranslatorOfertDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de TranslatorOfertEntity a convertir
     * @return Instancia de TranslatorOfertDTO con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationOfferDTO fullEntity2DTO(TranslationOfferEntity entity) {
        if (entity != null) {
            TranslationOfferDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de TranslatorOfertDTO a TranslatorOfertEntity.
     * Incluye todos los atributos de TranslatorOfertEntity.
     *
     * @param dto Instancia de TranslatorOfertDTO a convertir
     * @return Instancia de TranslatorOfertEntity con los datos recibidos por parámetro
     * @generated
     */
    public static TranslationOfferEntity fullDTO2Entity (TranslationOfferDTO dto) {
        if (dto != null) {
            TranslationOfferEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de TranslatorOfertEntity a TranslatorOfertDTO. Para cada
     * instancia de TranslatorOfertEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo TranslatorOfertDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de TranslatorOfertDTO
     * @generated
     */
    public static List<TranslationOfferDTO> listEntity2DTO(List<TranslationOfferEntity> entities) {
        List<TranslationOfferDTO> dtos = new ArrayList<TranslationOfferDTO>();
        if (entities != null) {
            for (TranslationOfferEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de TranslatorOfertDTO a instancias de
     * TranslatorOfertEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de TranslatorOfertDTO a convertir
     * @return Collección de instancias de TranslatorOfertEntity
     * @generated
     */
    public static List<TranslationOfferEntity> listDTO2Entity(List<TranslationOfferDTO> dtos) {
        List<TranslationOfferEntity> entities = new ArrayList<TranslationOfferEntity>();
        if (dtos != null) {
            for (TranslationOfferDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
