package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class TranslatorDTO {

    private Long id;
    private String name;
    private String picture;
    
    private String email;
    
    @PodamStrategyValue(DateStrategy.class)
    private Date birthDate;

    private List<EducationDTO> education = new ArrayList<>();
    @PodamExclude
    private List<LanguageDTO> languages = new ArrayList<>();
    @PodamExclude
    private List<KnowledgeAreaDTO> knowledgeAreas = new ArrayList<>();
    @PodamExclude
    private List<TranslatorOfertDTO> translatorOferts = new ArrayList<>();
    @PodamExclude
    private List<ReviewDTO> reviews;

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
    public String getPicture() {
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @generated
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @generated
     */
    public void setBirthDate(Date birthdate) {
        this.birthDate = birthdate;
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
    
    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public List<KnowledgeAreaDTO> getKnowledgeAreas() {
        return knowledgeAreas;
    }
    
    public void setKnowledgeAreas(List<KnowledgeAreaDTO> knowledgeAreas) {
        this.knowledgeAreas = knowledgeAreas;
    }
    
    public List<TranslatorOfertDTO> getTranslatorOferts() {
        return translatorOferts;
    }
    
    public void setTranslatorOferts(List<TranslatorOfertDTO> translatorOferts) {
        this.translatorOferts = translatorOferts;
    }
}
