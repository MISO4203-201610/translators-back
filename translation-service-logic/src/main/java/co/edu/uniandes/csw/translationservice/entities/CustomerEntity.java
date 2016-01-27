package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@Entity
public class CustomerEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToMany(mappedBy = "customer")
    private List<TranslationRequestEntity> translationRequests = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "customer")
    private List<CorrectionRequestEntity> correctionRequests = new ArrayList<>();

    /**
     * @generated
     */
    public List<TranslationRequestEntity> getTranslationRequests() {
        return translationRequests;
    }

    /**
     * @generated
     */
    public void setTranslationRequests(List<TranslationRequestEntity> translationrequests) {
        this.translationRequests = translationrequests;
    }

    /**
     * @generated
     */
    public List<CorrectionRequestEntity> getCorrectionRequests() {
        return correctionRequests;
    }

    /**
     * @generated
     */
    public void setCorrectionRequests(List<CorrectionRequestEntity> correctionrequests) {
        this.correctionRequests = correctionrequests;
    }


    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((CustomerEntity)obj).getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
