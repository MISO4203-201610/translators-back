package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import co.edu.uniandes.csw.auth.model.DateAdapter;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;

/**
 * @generated
 */
@XmlRootElement
public class TranslationRequestDTO {

    private Long id;
    private String name;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;
    @PodamExclude
    private CustomerDTO customer;
    @PodamExclude
    private StatusDTO status;
    @PodamExclude
    private LanguageDTO originalLanguage;
    @PodamExclude
    private LanguageDTO targetLanguage;

    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @generated
     */
    public void setCreationDate(Date creationdate) {
        this.creationDate = creationdate;
    }

    /**
     * @generated
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @generated
     */
    public void setDueDate(Date duedate) {
        this.dueDate = duedate;
    }

    /**
     * @generated
     */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * @generated
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    /**
     * @generated
     */
    public StatusDTO getStatus() {
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    /**
     * @generated
     */
    public LanguageDTO getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * @generated
     */
    public void setOriginalLanguage(LanguageDTO originallanguage) {
        this.originalLanguage = originallanguage;
    }

    /**
     * @generated
     */
    public LanguageDTO getTargetLanguage() {
        return targetLanguage;
    }

    /**
     * @generated
     */
    public void setTargetLanguage(LanguageDTO targetlanguage) {
        this.targetLanguage = targetlanguage;
    }

}
