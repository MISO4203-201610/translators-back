package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.KnowledgeAreaDTO;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class KnowledgeAreaConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private KnowledgeAreaConverter() {
    }

    /**
     * Realiza la conversión de KnowledgeAreaEntity a KnowledgeAreaDTO.
     * Se invoca cuando otra entidad tiene una referencia a KnowledgeAreaEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de KnowledgeAreaEntity a convertir
     * @return instancia de KnowledgeAreaDTO con los datos recibidos por parámetro
     * @generated
     */
    public static KnowledgeAreaDTO refEntity2DTO(KnowledgeAreaEntity entity) {
        if (entity != null) {
            KnowledgeAreaDTO dto = new KnowledgeAreaDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de KnowledgeAreaDTO a KnowledgeAreaEntity Se invoca cuando otro DTO
     * tiene una referencia a KnowledgeAreaDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de KnowledgeAreaDTO a convertir
     * @return instancia de KnowledgeAreaEntity con los datos recibidos por parámetro
     * @generated
     */
    public static KnowledgeAreaEntity refDTO2Entity(KnowledgeAreaDTO dto) {
        if (dto != null) {
            KnowledgeAreaEntity entity = new KnowledgeAreaEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de KnowledgeAreaEntity a KnowledgeAreaDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de KnowledgeAreaEntity a convertir
     * @return Instancia de KnowledgeAreaDTO con los datos recibidos por parámetro
     * @generated
     */
    private static KnowledgeAreaDTO basicEntity2DTO(KnowledgeAreaEntity entity) {
        if (entity != null) {
            KnowledgeAreaDTO dto = new KnowledgeAreaDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de KnowledgeAreaDTO a KnowledgeAreaEntity Se invoca cuando se
     * necesita convertir una instancia de KnowledgeAreaDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de KnowledgeAreaDTO a convertir
     * @return Instancia de KnowledgeAreaEntity creada a partir de los datos de dto
     * @generated
     */
    private static KnowledgeAreaEntity basicDTO2Entity(KnowledgeAreaDTO dto) {
        if (dto != null) {
            KnowledgeAreaEntity entity = new KnowledgeAreaEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de KnowledgeAreaEntity a KnowledgeAreaDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de KnowledgeAreaEntity a convertir
     * @return Instancia de KnowledgeAreaDTO con los datos recibidos por parámetro
     * @generated
     */
    public static KnowledgeAreaDTO fullEntity2DTO(KnowledgeAreaEntity entity) {
        if (entity != null) {
            KnowledgeAreaDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de KnowledgeAreaDTO a KnowledgeAreaEntity.
     * Incluye todos los atributos de KnowledgeAreaEntity.
     *
     * @param dto Instancia de KnowledgeAreaDTO a convertir
     * @return Instancia de KnowledgeAreaEntity con los datos recibidos por parámetro
     * @generated
     */
    public static KnowledgeAreaEntity fullDTO2Entity(KnowledgeAreaDTO dto) {
        if (dto != null) {
            KnowledgeAreaEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de KnowledgeAreaEntity a KnowledgeAreaDTO. Para cada
     * instancia de KnowledgeAreaEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo KnowledgeAreaDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de KnowledgeAreaDTO
     * @generated
     */
    public static List<KnowledgeAreaDTO> listEntity2DTO(List<KnowledgeAreaEntity> entities) {
        List<KnowledgeAreaDTO> dtos = new ArrayList<KnowledgeAreaDTO>();
        if (entities != null) {
            for (KnowledgeAreaEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de KnowledgeAreaDTO a instancias de
     * KnowledgeAreaEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de KnowledgeAreaDTO a convertir
     * @return Collección de instancias de KnowledgeAreaEntity
     * @generated
     */
    public static List<KnowledgeAreaEntity> listDTO2Entity(List<KnowledgeAreaDTO> dtos) {
        List<KnowledgeAreaEntity> entities = new ArrayList<KnowledgeAreaEntity>();
        if (dtos != null) {
            for (KnowledgeAreaDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}