package co.edu.uniandes.csw.translationservice.dtos;

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

}
