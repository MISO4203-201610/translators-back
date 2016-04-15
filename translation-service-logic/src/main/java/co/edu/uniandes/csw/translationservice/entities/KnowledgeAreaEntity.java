package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@Entity
public class KnowledgeAreaEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne
    private TranslationRequestEntity traslationRequest;
    
    @PodamExclude
    @ManyToOne
    private CorrectionRequestEntity correctionRequest;

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

    /**
     * @return the correctionRequest
     */
    public CorrectionRequestEntity getCorrectionRequest() {
        return correctionRequest;
    }

    /**
     * @param correctionRequest the correctionRequest to set
     */
    public void setCorrectionRequest(CorrectionRequestEntity correctionRequest) {
        this.correctionRequest = correctionRequest;
    }
    
}
