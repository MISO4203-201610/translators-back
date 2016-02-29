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
public class EducationEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date endDate;

    private String institution;

    private String title;

    private String description;

    @PodamExclude
    @ManyToOne
    private TranslatorEntity translator;

    /**
     * @generated
     */
    public Date getStartDate(){
        return startDate;
    }

    /**
     * @generated
     */
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    /**
     * @generated
     */
    public Date getEndDate(){
        return endDate;
    }

    /**
     * @generated
     */
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    /**
     * @generated
     */
    public String getInstitution(){
        return institution;
    }

    /**
     * @generated
     */
    public void setInstitution(String institution){
        this.institution = institution;
    }

    /**
     * @generated
     */
    public String getTitle(){
        return title;
    }

    /**
     * @generated
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * @generated
     */
    public String getDescription(){
        return description;
    }

    /**
     * @generated
     */
    public void setDescription(String description){
        this.description = description;
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
}
