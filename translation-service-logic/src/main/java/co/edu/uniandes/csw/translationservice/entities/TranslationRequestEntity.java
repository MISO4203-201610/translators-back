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
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class TranslationRequestEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;

    @PodamExclude
    @ManyToOne
    private LanguageEntity originalLanguage;

    @PodamExclude
    @ManyToOne
    private StatusEntity status;

    @PodamExclude
    @ManyToOne
    private CustomerEntity customer;

    @PodamExclude
    @ManyToOne
    private LanguageEntity targetLanguage;
    
    private String description;

    /**
     * @generated
     */
    public Date getCreationDate(){
        return creationDate;
    }

    /**
     * @generated
     */
    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    /**
     * @generated
     */
    public Date getDueDate(){
        return dueDate;
    }

    /**
     * @generated
     */
    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }

    /**
     * @generated
     */
    public LanguageEntity getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * @generated
     */
    public void setOriginalLanguage(LanguageEntity originallanguage) {
        this.originalLanguage = originallanguage;
    }

    /**
     * @generated
     */
    public StatusEntity getStatus() {
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    /**
     * @generated
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * @generated
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * @generated
     */
    public LanguageEntity getTargetLanguage() {
        return targetLanguage;
    }

    /**
     * @generated
     */
    public void setTargetLanguage(LanguageEntity targetlanguage) {
        this.targetLanguage = targetlanguage;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
