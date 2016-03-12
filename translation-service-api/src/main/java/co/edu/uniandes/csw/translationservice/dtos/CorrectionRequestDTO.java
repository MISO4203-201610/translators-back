package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class CorrectionRequestDTO extends RequestDTO {

    @PodamExclude
    private LanguageDTO language;
    
    @PodamExclude
    private String desctiption;
    
    @PodamExclude
    private int numberOfWords;

    /**
     * @generated
     */
    public LanguageDTO getLanguage() {
        return language;
    }

    /**
     * @generated
     */
    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }
    
    
}
