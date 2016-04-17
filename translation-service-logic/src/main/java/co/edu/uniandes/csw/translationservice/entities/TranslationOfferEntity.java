package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 *  @author Germán
 */
@Entity
public class TranslationOfferEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne
    private TranslatorEntity translationOfferTranslator;
    
    @PodamExclude
    @ManyToOne
    private TranslationRequestEntity translationOfferRequest;
    
    private String price;
    
    private String comment;
    
    /**
     * @generated
     */
    public String getComment() {
        return comment;
    }

    /**
     * @generated
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /**
     * @generated
     */
    public String getPrice() {
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
    /**
     * @generated
     */
    public TranslatorEntity getTranslator() {
        return translationOfferTranslator;
    }

    /**
     * @generated
     */
    public void setTranslator(TranslatorEntity translator) {
        this.translationOfferTranslator = translator;
    }
    
    /**
     * @generated
     */
    public TranslationRequestEntity getTranslationRequest() {
        return translationOfferRequest;
    }

    /**
     * @generated
     */
    public void setTranslationRequest(TranslationRequestEntity translationRequest) {
        this.translationOfferRequest = translationRequest;
    }    
}