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

    private String status;

    @PodamExclude
    @ManyToOne
    private CustomerEntity customer;

    @PodamExclude
    @ManyToOne
    private LanguageEntity language;

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
    public String getStatus(){
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(String status){
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
    public LanguageEntity getLanguage() {
        return language;
    }

    /**
     * @generated
     */
    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }


    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((CorrectionRequestEntity)obj).getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
