package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.InvitationDTO;
import co.edu.uniandes.csw.translationservice.entities.InvitationEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class InvitationConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private InvitationConverter() {
    }

    /**
     * Realiza la conversión de InvitationEntity a InvitationDTO.
     * Se invoca cuando otra entidad tiene una referencia a InvitationEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de InvitationEntity a convertir
     * @return instancia de InvitationDTO con los datos recibidos por parámetro
     * @generated
     */
    public static InvitationDTO refEntity2DTO(InvitationEntity entity) {
        if (entity != null) {
            InvitationDTO dto = new InvitationDTO();
            dto.setId(entity.getId());
            dto.setAccepted(entity.isAccepted());
            dto.setTranslatorOfert(TranslatorOfertConverter.fullEntity2DTO(entity.getTranslatorOfert()));
            dto.setTranslator(TranslatorConverter.fullEntity2DTO(entity.getTranslator()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de InvitationDTO a InvitationEntity Se invoca cuando otro DTO
     * tiene una referencia a InvitationDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de InvitationDTO a convertir
     * @return instancia de InvitationEntity con los datos recibidos por parámetro
     * @generated
     */
    public static InvitationEntity refDTO2Entity(InvitationDTO dto) {
        if (dto != null) {
            InvitationEntity entity = new InvitationEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de InvitationEntity a InvitationDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de InvitationEntity a convertir
     * @return Instancia de InvitationDTO con los datos recibidos por parámetro
     * @generated
     */
    private static InvitationDTO basicEntity2DTO(InvitationEntity entity) {
        if (entity != null) {
            InvitationDTO dto = new InvitationDTO();
            dto.setId(entity.getId());
            dto.setAccepted(entity.isAccepted());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de InvitationDTO a InvitationEntity Se invoca cuando se
     * necesita convertir una instancia de InvitationDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de InvitationDTO a convertir
     * @return Instancia de InvitationEntity creada a partir de los datos de dto
     * @generated
     */
    private static InvitationEntity basicDTO2Entity(InvitationDTO dto) {
        if (dto != null) {
            InvitationEntity entity = new InvitationEntity();
            entity.setId(dto.getId());
            entity.setAccepted(dto.isAccepted());
            entity.setTranslatorOfert(TranslatorOfertConverter.fullDTO2Entity(dto.getTranslatorOfert()));
            entity.setTranslator(TranslatorConverter.fullDTO2Entity(dto.getTranslator()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de InvitationEntity a InvitationDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de InvitationEntity a convertir
     * @return Instancia de InvitationDTO con los datos recibidos por parámetro
     * @generated
     */
    public static InvitationDTO fullEntity2DTO(InvitationEntity entity) {
        if (entity != null) {
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de InvitationDTO a InvitationEntity.
     * Incluye todos los atributos de InvitationEntity.
     *
     * @param dto Instancia de InvitationDTO a convertir
     * @return Instancia de InvitationEntity con los datos recibidos por parámetro
     * @generated
     */
    public static InvitationEntity fullDTO2Entity(InvitationDTO dto) {
        if (dto != null) {
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de InvitationEntity a InvitationDTO. Para cada
     * instancia de InvitationEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo InvitationDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de InvitationDTO
     * @generated
     */
    public static List<InvitationDTO> listEntity2DTO(List<InvitationEntity> entities) {
        List<InvitationDTO> dtos = new ArrayList<InvitationDTO>();
        if (entities != null) {
            for (InvitationEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de InvitationDTO a instancias de
     * InvitationEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de InvitationDTO a convertir
     * @return Collección de instancias de InvitationEntity
     * @generated
     */
    public static List<InvitationEntity> listDTO2Entity(List<InvitationDTO> dtos) {
        List<InvitationEntity> entities = new ArrayList<InvitationEntity>();
        if (dtos != null) {
            for (InvitationDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
