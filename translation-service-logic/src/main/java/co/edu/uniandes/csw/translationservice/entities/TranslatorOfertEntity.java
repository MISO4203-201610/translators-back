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
public class TranslatorOfertEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne
    private TranslatorEntity translator;
    
    @PodamExclude
    @ManyToOne
    private TranslationRequestEntity translationRequest;
    
    private String price;
    
    private String comment;
    
    private boolean acceptedByCustomer;
    
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
        return translator;
    }

    /**
     * @generated
     */
    public void setTranslator(TranslatorEntity translator) {
        this.translator = translator;
    }
    
    /**
     * @generated
     */
    public TranslationRequestEntity getTranslationRequest() {
        return translationRequest;
    }

    /**
     * @generated
     */
    public void setTranslationRequest(TranslationRequestEntity translationRequest) {
        this.translationRequest = translationRequest;
    }    

    public boolean isAcceptedByCustomer() {
        return acceptedByCustomer;
    }

    public void setAcceptedByCustomer(boolean acceptedByCustomer) {
        this.acceptedByCustomer = acceptedByCustomer;
    }
       
}