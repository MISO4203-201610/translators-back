package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.EducationDTO;
import co.edu.uniandes.csw.translationservice.entities.EducationEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;

/**
 * @generated
 */
public abstract class EducationConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private EducationConverter() {
    }

    /**
     * Realiza la conversión de EducationEntity a EducationDTO.
     * Se invoca cuando otra entidad tiene una referencia a EducationEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de EducationEntity a convertir
     * @return instancia de EducationDTO con los datos recibidos por parámetro
     * @generated
     */
    public static EducationDTO refEntity2DTO(EducationEntity entity) {
        if (entity != null) {
            EducationDTO dto = new EducationDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());
            dto.setInstitution(entity.getInstitution());
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de EducationDTO a EducationEntity Se invoca cuando otro DTO
     * tiene una referencia a EducationDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de EducationDTO a convertir
     * @return instancia de EducationEntity con los datos recibidos por parámetro
     * @generated
     */
    public static EducationEntity refDTO2Entity(EducationDTO dto) {
        if (dto != null) {
            EducationEntity entity = new EducationEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de EducationEntity a EducationDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de EducationEntity a convertir
     * @return Instancia de EducationDTO con los datos recibidos por parámetro
     * @generated
     */
    private static EducationDTO basicEntity2DTO(EducationEntity entity) {
        if (entity != null) {
            EducationDTO dto = new EducationDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());
            dto.setInstitution(entity.getInstitution());
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setTranslator(TranslatorConverter.refEntity2DTO(entity.getTranslator()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de EducationDTO a EducationEntity Se invoca cuando se
     * necesita convertir una instancia de EducationDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de EducationDTO a convertir
     * @return Instancia de EducationEntity creada a partir de los datos de dto
     * @generated
     */
    private static EducationEntity basicDTO2Entity(EducationDTO dto) {
        if (dto != null) {
            EducationEntity entity = new EducationEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity.setInstitution(dto.getInstitution());
            entity.setTitle(dto.getTitle());
            entity.setDescription(dto.getDescription());
            entity.setTranslator(TranslatorConverter.refDTO2Entity(dto.getTranslator()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de EducationEntity a EducationDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de EducationEntity a convertir
     * @return Instancia de EducationDTO con los datos recibidos por parámetro
     * @generated
     */
    public static EducationDTO fullEntity2DTO(EducationEntity entity) {
        if (entity != null) {
            EducationDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de EducationDTO a EducationEntity.
     * Incluye todos los atributos de EducationEntity.
     *
     * @param dto Instancia de EducationDTO a convertir
     * @return Instancia de EducationEntity con los datos recibidos por parámetro
     * @generated
     */
    public static EducationEntity fullDTO2Entity(EducationDTO dto) {
        if (dto != null) {
            EducationEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de EducationEntity a EducationDTO. Para cada
     * instancia de EducationEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo EducationDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de EducationDTO
     * @generated
     */
    public static List<EducationDTO> listEntity2DTO(List<EducationEntity> entities) {
        List<EducationDTO> dtos = new ArrayList<EducationDTO>();
        if (entities != null) {
            for (EducationEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de EducationDTO a instancias de
     * EducationEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de EducationDTO a convertir
     * @return Collección de instancias de EducationEntity
     * @generated
     */
    public static List<EducationEntity> listDTO2Entity(List<EducationDTO> dtos) {
        List<EducationEntity> entities = new ArrayList<EducationEntity>();
        if (dtos != null) {
            for (EducationDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * Convierte una instancia de EducationDTO a EducationEntity asignando un valor
     * al atributo org.eclipse.uml2.uml.internal.impl.PropertyImpl@b315498 (name: translator, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false, isDerivedUnion: false, isID: false) de EducationEntity. Se usa cuando se necesita convertir
     * un EducationDTO asignando el libro asociado
     * @param dto Instancia de EducationDTO
     * @param parent Instancia de TranslatorEntity
     * @return Instancia de EducationEntity con TranslatorEntity asociado
     * @generated
     */
    public static EducationEntity childDTO2Entity(EducationDTO dto, TranslatorEntity parent){
        EducationEntity entity = basicDTO2Entity(dto);
        entity.setTranslator(parent);
        return entity;
    }

    /**
     * Convierte una colección de instancias de EducationDTO a EducationEntity
     * asignando el mismo padre para todos. Se usa cuando se necesita crear o
     * actualizar varios EducationEntity con el mismo org.eclipse.uml2.uml.internal.impl.PropertyImpl@b315498 (name: translator, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false, isDerivedUnion: false, isID: false)
     * @param dtos Colección de instancias de EducationDTO
     * @param parent Instancia de TranslatorEntity
     * @return Colección de EducationEntity con el atributo org.eclipse.uml2.uml.internal.impl.PropertyImpl@b315498 (name: translator, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false, isDerivedUnion: false, isID: false) asignado
     * @generated
     */
    public static List<EducationEntity> childListDTO2Entity(List<EducationDTO> dtos, TranslatorEntity parent) {
        List<EducationEntity> entities = new ArrayList<EducationEntity>();
        if (dtos != null) {
            for (EducationDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
