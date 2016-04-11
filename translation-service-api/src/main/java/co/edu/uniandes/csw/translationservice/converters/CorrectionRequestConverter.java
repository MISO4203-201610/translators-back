package co.edu.uniandes.csw.translationservice.converters;

import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import co.edu.uniandes.csw.translationservice.dtos.CorrectionRequestDTO;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class CorrectionRequestConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private CorrectionRequestConverter() {
    }

    /**
     * Realiza la conversión de CorrectionRequestEntity a CorrectionRequestDTO.
     * Se invoca cuando otra entidad tiene una referencia a CorrectionRequestEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de CorrectionRequestEntity a convertir
     * @return instancia de CorrectionRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CorrectionRequestDTO refEntity2DTO(CorrectionRequestEntity entity) {
        if (entity != null) {
            CorrectionRequestDTO dto = new CorrectionRequestDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreationDate(entity.getCreationDate());
            dto.setDueDate(entity.getDueDate());
            dto.setDesctiption(entity.getDescription());
            dto.setNumberOfWords(entity.getNumberOfWords());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de CorrectionRequestDTO a CorrectionRequestEntity Se invoca cuando otro DTO
     * tiene una referencia a CorrectionRequestDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de CorrectionRequestDTO a convertir
     * @return instancia de CorrectionRequestEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CorrectionRequestEntity refDTO2Entity(CorrectionRequestDTO dto) {
        if (dto != null) {
            CorrectionRequestEntity entity = new CorrectionRequestEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CorrectionRequestEntity a CorrectionRequestDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de CorrectionRequestEntity a convertir
     * @return Instancia de CorrectionRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    private static CorrectionRequestDTO basicEntity2DTO(CorrectionRequestEntity entity) {
        if (entity != null) {
            CorrectionRequestDTO dto = new CorrectionRequestDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreationDate(entity.getCreationDate());
            dto.setDueDate(entity.getDueDate());
            dto.setStatus(StatusConverter.refEntity2DTO(entity.getStatus()));
            dto.setLanguage(LanguageConverter.refEntity2DTO(entity.getLanguage()));
            dto.setCustomer(CustomerConverter.refEntity2DTO(entity.getCustomer()));
            dto.setDesctiption(entity.getDescription());
            dto.setNumberOfWords(entity.getNumberOfWords());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CorrectionRequestDTO a CorrectionRequestEntity Se invoca cuando se
     * necesita convertir una instancia de CorrectionRequestDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de CorrectionRequestDTO a convertir
     * @return Instancia de CorrectionRequestEntity creada a partir de los datos de dto
     * @generated
     */
    private static CorrectionRequestEntity basicDTO2Entity(CorrectionRequestDTO dto) {
        if (dto != null) {
            CorrectionRequestEntity entity = new CorrectionRequestEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setCreationDate(dto.getCreationDate());
            entity.setDueDate(dto.getDueDate());
            entity.setStatus(StatusConverter.refDTO2Entity(dto.getStatus()));
            entity.setLanguage(LanguageConverter.refDTO2Entity(dto.getLanguage()));
            entity.setCustomer(CustomerConverter.refDTO2Entity(dto.getCustomer()));
            entity.setDescription(dto.getDesctiption());
            entity.setNumberOfWords(dto.getNumberOfWords());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de CorrectionRequestEntity a CorrectionRequestDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de CorrectionRequestEntity a convertir
     * @return Instancia de CorrectionRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CorrectionRequestDTO fullEntity2DTO(CorrectionRequestEntity entity) {
        if (entity != null) {
            CorrectionRequestDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CorrectionRequestDTO a CorrectionRequestEntity.
     * Incluye todos los atributos de CorrectionRequestEntity.
     *
     * @param dto Instancia de CorrectionRequestDTO a convertir
     * @return Instancia de CorrectionRequestEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CorrectionRequestEntity fullDTO2Entity(CorrectionRequestDTO dto) {
        if (dto != null) {
            CorrectionRequestEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de CorrectionRequestEntity a CorrectionRequestDTO. Para cada
     * instancia de CorrectionRequestEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo CorrectionRequestDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de CorrectionRequestDTO
     * @generated
     */
    public static List<CorrectionRequestDTO> listEntity2DTO(List<CorrectionRequestEntity> entities) {
        List<CorrectionRequestDTO> dtos = new ArrayList<CorrectionRequestDTO>();
        if (entities != null) {
            for (CorrectionRequestEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de CorrectionRequestDTO a instancias de
     * CorrectionRequestEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de CorrectionRequestDTO a convertir
     * @return Collección de instancias de CorrectionRequestEntity
     * @generated
     */
    public static List<CorrectionRequestEntity> listDTO2Entity(List<CorrectionRequestDTO> dtos) {
        List<CorrectionRequestEntity> entities = new ArrayList<CorrectionRequestEntity>();
        if (dtos != null) {
            for (CorrectionRequestDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    /**
     * Convierte una instancias de CorrectionRequestEntity a instancias de
     * TranslatorDTO que representan los traductores a invitar
     *
     * @param dto Correction Request DTO
     * @param list Colección de traductores
     * @return Colección de instancias de TranslatorDTO filtrados
     */
    public static List<TranslatorDTO> fullEntity2RecommendationDTO(CorrectionRequestDTO dto, List<TranslatorDTO> list) {
        List<TranslatorDTO> dtos = new ArrayList<TranslatorDTO>();
        
        if (list != null)
        {
            for (TranslatorDTO translatorDto : list) {
                
                // Add it!
                if (translatorDto.getLanguages().contains(dto.getLanguage()))
                    dtos.add(translatorDto);
            }
        }
        
        return dtos;
    }
}
