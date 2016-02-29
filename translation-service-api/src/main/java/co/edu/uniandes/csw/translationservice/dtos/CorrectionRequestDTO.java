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
}
