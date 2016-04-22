package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @Germán
 */
@XmlRootElement
public class TranslatorOfertDTO {
    
    private Long id;
    private String price;
    private String comment;
    
    @PodamExclude
    private TranslatorDTO translator;
    
    @PodamExclude
    private TranslationRequestDTO translationRequest;
    

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @generated
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @generated
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @generated
     */
    public TranslatorDTO getTranslator() {
        return translator;
    }

    /**
     * @generated
     */
    public void setTranslator(TranslatorDTO translator) {
        this.translator = translator;
    }

    /**
     * @generated
     */
    public TranslationRequestDTO getTranslationRequest() {
        return translationRequest;
    }

    /**
     * @generated
     */
    public void setTranslationRequest(TranslationRequestDTO translationRequest) {
        this.translationRequest = translationRequest;
    }

}
