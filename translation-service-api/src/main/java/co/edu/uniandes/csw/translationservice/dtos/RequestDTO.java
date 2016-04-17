package co.edu.uniandes.csw.translationservice.dtos;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@XmlRootElement
@XmlSeeAlso({TranslationRequestDTO.class, CorrectionRequestDTO.class})
public abstract class RequestDTO {

    private Long id;
    private String name;
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;
    @PodamExclude
    private StatusDTO status;
    @PodamExclude
    private CustomerDTO customer;
    
    private String description;
    private int numberOfWords;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the status
     */
    public StatusDTO getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    /**
     * @return the customer
     */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }
    
    
}
