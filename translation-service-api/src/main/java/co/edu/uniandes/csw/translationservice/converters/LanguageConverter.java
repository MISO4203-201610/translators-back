package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.LanguageDTO;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class LanguageConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private LanguageConverter() {
    }

    /**
     * Realiza la conversión de LanguageEntity a LanguageDTO.
     * Se invoca cuando otra entidad tiene una referencia a LanguageEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de LanguageEntity a convertir
     * @return instancia de LanguageDTO con los datos recibidos por parámetro
     * @generated
     */
    public static LanguageDTO refEntity2DTO(LanguageEntity entity) {
        if (entity != null) {
            LanguageDTO dto = new LanguageDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de LanguageDTO a LanguageEntity Se invoca cuando otro DTO
     * tiene una referencia a LanguageDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de LanguageDTO a convertir
     * @return instancia de LanguageEntity con los datos recibidos por parámetro
     * @generated
     */
    public static LanguageEntity refDTO2Entity(LanguageDTO dto) {
        if (dto != null) {
            LanguageEntity entity = new LanguageEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de LanguageEntity a LanguageDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de LanguageEntity a convertir
     * @return Instancia de LanguageDTO con los datos recibidos por parámetro
     * @generated
     */
    private static LanguageDTO basicEntity2DTO(LanguageEntity entity) {
        if (entity != null) {
            LanguageDTO dto = new LanguageDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de LanguageDTO a LanguageEntity Se invoca cuando se
     * necesita convertir una instancia de LanguageDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de LanguageDTO a convertir
     * @return Instancia de LanguageEntity creada a partir de los datos de dto
     * @generated
     */
    private static LanguageEntity basicDTO2Entity(LanguageDTO dto) {
        if (dto != null) {
            LanguageEntity entity = new LanguageEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de LanguageEntity a LanguageDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de LanguageEntity a convertir
     * @return Instancia de LanguageDTO con los datos recibidos por parámetro
     * @generated
     */
    public static LanguageDTO fullEntity2DTO(LanguageEntity entity) {
        if (entity != null) {
            LanguageDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de LanguageDTO a LanguageEntity.
     * Incluye todos los atributos de LanguageEntity.
     *
     * @param dto Instancia de LanguageDTO a convertir
     * @return Instancia de LanguageEntity con los datos recibidos por parámetro
     * @generated
     */
    public static LanguageEntity fullDTO2Entity(LanguageDTO dto) {
        if (dto != null) {
            LanguageEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de LanguageEntity a LanguageDTO. Para cada
     * instancia de LanguageEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo LanguageDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de LanguageDTO
     * @generated
     */
    public static List<LanguageDTO> listEntity2DTO(List<LanguageEntity> entities) {
        List<LanguageDTO> dtos = new ArrayList<LanguageDTO>();
        if (entities != null) {
            for (LanguageEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de LanguageDTO a instancias de
     * LanguageEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de LanguageDTO a convertir
     * @return Collección de instancias de LanguageEntity
     * @generated
     */
    public static List<LanguageEntity> listDTO2Entity(List<LanguageDTO> dtos) {
        List<LanguageEntity> entities = new ArrayList<LanguageEntity>();
        if (dtos != null) {
            for (LanguageDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
