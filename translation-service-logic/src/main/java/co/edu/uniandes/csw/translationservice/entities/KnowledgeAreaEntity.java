package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@Entity
public class KnowledgeAreaEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne
    private TranslationRequestEntity traslationRequest;

    /**
     * @return the traslationRequest
     */
    public TranslationRequestEntity getTraslationRequest() {
        return traslationRequest;
    }

    /**
     * @param traslationRequest the traslationRequest to set
     */
    public void setTraslationRequest(TranslationRequestEntity traslationRequest) {
        this.traslationRequest = traslationRequest;
    }
    
}
