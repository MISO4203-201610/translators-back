package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class CustomerDTO extends PersonDTO {

    @PodamExclude
    private List<TranslationRequestDTO> translationRequests = new ArrayList<>();
    @PodamExclude
    private List<CorrectionRequestDTO> correctionRequests = new ArrayList<>();

    /**
     * @generated
     */
    public List<TranslationRequestDTO> getTranslationRequests() {
        return translationRequests;
    }

    /**
     * @generated
     */
    public void setTranslationRequests(List<TranslationRequestDTO> translationrequests) {
        this.translationRequests = translationrequests;
    }

    /**
     * @generated
     */
    public List<CorrectionRequestDTO> getCorrectionRequests() {
        return correctionRequests;
    }

    /**
     * @generated
     */
    public void setCorrectionRequests(List<CorrectionRequestDTO> correctionrequests) {
        this.correctionRequests = correctionrequests;
    }

}
