package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;

/**
 * @generated
 */
@Entity
public class TranslatorEntity extends BaseEntity implements Serializable {

    private String picture;
    
    @PodamExclude
    @OneToMany(mappedBy = "translator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date birthDate;

    
    @OneToMany(mappedBy = "translator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationEntity> education = new ArrayList<>();

    @PodamExclude
    @OneToMany
    private List<LanguageEntity> languages = new ArrayList<>();
    
    @PodamExclude
    @OneToMany
    private List<KnowledgeAreaEntity> knowledgeAreas = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "translator")
    private List<TranslationOfferEntity> translationOffers = new ArrayList<>();
    
    private String email;

    /**
     * @generated
     */
    public String getPicture(){
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture){
        this.picture = picture;
    }

    /**
     * @generated
     */
    public Date getBirthDate(){
        return birthDate;
    }

    /**
     * @generated
     */
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

    /**
     * @generated
     */
    public List<EducationEntity> getEducation() {
        return education;
    }

    /**
     * @generated
     */
    public void setEducation(List<EducationEntity> education) {
        this.education = education;
    }

    /**
     * @generated
     */
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    /**
     * @generated
     */
    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }
    
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
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
    
    public List<KnowledgeAreaEntity> getKnowledgeAreas() {
        return knowledgeAreas;
    }
    
    public void setKnowledgeAreas(List<KnowledgeAreaEntity> knowledgeAreas) {
        this.knowledgeAreas = knowledgeAreas;
    }
    
    public List<TranslationOfferEntity> getTranslationOffers() {
        return translationOffers;
    }
    
    public void setTranslationOffers(List<TranslationOfferEntity> translatorOferts) {
        this.translationOffers = translatorOferts;
    }
}
