package co.edu.uniandes.csw.translationservice.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class TranslationRequestDTO extends RequestDTO {

    @PodamExclude
    private LanguageDTO originalLanguage;
    @PodamExclude
    private LanguageDTO targetLanguage;
    
    private String description;
    private String contexto;
    private int numberOfWords;
    
    @PodamExclude
    private List<KnowledgeAreaDTO> knowledgeAreas = new ArrayList<>();

    @PodamExclude
    private List<TranslationOfferDTO> translationOffers = new ArrayList<TranslationOfferDTO>();
    
    /**
     * @generated
     */
    public LanguageDTO getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * @generated
     */
    public void setOriginalLanguage(LanguageDTO originallanguage) {
        this.originalLanguage = originallanguage;
    }

    /**
     * @generated
     */
    public LanguageDTO getTargetLanguage() {
        return targetLanguage;
    }

    /**
     * @generated
     */
    public void setTargetLanguage(LanguageDTO targetlanguage) {
        this.targetLanguage = targetlanguage;
    }

    /**
     * @return the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the contexto
     */
    public String getContexto() {
        return contexto;
    }

    /**
     * @param contexto the contexto to set
     */
    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    @Override
    public int getNumberOfWords() {
        return numberOfWords;
    }

    @Override
    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    /**
     * @return the knowledgeAreas
     */
    public List<KnowledgeAreaDTO> getKnowledgeAreas() {
        return knowledgeAreas;
    }

    /**
     * @param knowledgeAreas the knowledgeAreas to set
     */
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
