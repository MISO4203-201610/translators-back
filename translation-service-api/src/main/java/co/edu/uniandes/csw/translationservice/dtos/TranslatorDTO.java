package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class TranslatorDTO {

    private Long id;
    private String name;
    @PodamExclude
    private List<LanguageDTO> languages = new ArrayList<>();
    
    private List<EducationDTO> education = new ArrayList<>();

    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public List<LanguageDTO> getLanguages() {
        return languages;
    }

    /**
     * @generated
     */
    public void setLanguages(List<LanguageDTO> languages) {
        this.languages = languages;
    }

    /**
     * @generated
     */
    public List<EducationDTO> getEducation() {
        return education;
    }

    /**
     * @generated
     */
    public void setEducation(List<EducationDTO> education) {
        this.education = education;
    }

}
