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
public class CorrectionRequestEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;

    @PodamExclude
    @ManyToOne
    private StatusEntity status;

    @PodamExclude
    @ManyToOne
    private LanguageEntity language;

    @PodamExclude
    @ManyToOne
    private CustomerEntity customer;
    
    private String description;
    
    private int numberOfWords;

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
    public LanguageEntity getLanguage() {
        return language;
    }

    /**
     * @generated
     */
    public void setLanguage(LanguageEntity language) {
        this.language = language;
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

    /**
     * @return the numeroPalabras
     */
    public int getNumberOfWords() {
        return numberOfWords;
    }

    /**
     * @param numeroPalabras the numero palabras to set
     */
    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }
}
