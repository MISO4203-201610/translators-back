package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@Entity
public class CustomerEntity extends BaseEntity implements Serializable {

    private String picture;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date birthDate;

    @PodamExclude
    @OneToMany(mappedBy = "customer")
    private List<TranslationRequestEntity> translationRequests = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "customer")
    private List<CorrectionRequestEntity> correctionRequests = new ArrayList<>();

    /**
     * @generated
     */
    public String getPicture(){
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture){
        this.picture = picture;
    }

    /**
     * @generated
     */
    public Date getBirthDate(){
        return birthDate;
    }

    /**
     * @generated
     */
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

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
}
