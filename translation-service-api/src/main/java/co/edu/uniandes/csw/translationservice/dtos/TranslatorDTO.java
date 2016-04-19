package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class TranslatorDTO extends PersonDTO {

    private String email;
    
    private List<EducationDTO> education = new ArrayList<>();
    @PodamExclude
    private List<LanguageDTO> languages = new ArrayList<>();
    @PodamExclude
    private List<KnowledgeAreaDTO> knowledgeAreas = new ArrayList<>();
    @PodamExclude
    private List<TranslationOfferDTO> translationOffers = new ArrayList<TranslationOfferDTO>();
    @PodamExclude
    private List<ReviewDTO> reviews;

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
    
    public List<TranslationOfferDTO> getTranslationOffers() {
        return translationOffers;
    }
    
    public void setTranslationOffers(List<TranslationOfferDTO> translationOffers) {
        this.translationOffers = translationOffers;
    }
}
