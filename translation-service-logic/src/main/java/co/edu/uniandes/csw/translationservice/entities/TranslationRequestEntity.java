package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.ArrayList;
import java.util.List;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    
    @PodamExclude
    @OneToMany(mappedBy = "translationOfferRequest")
    private List<TranslationOfferEntity> translationOffers = new ArrayList<>();
    
    private String description;
    
    private String contexto;
    
    private int numberOfWords;
    
    @PodamExclude
    @OneToMany
    private List<KnowledgeAreaEntity> knowledgeAreasRequested = new ArrayList<>();

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
    
    /**
     * @return the contexto
     */
    public String getContexto() {
        return contexto;
    }

    /**
     * @param contexto the contexto to set
     */
    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    /**
     * @return the knowledgeAreasRequested
     */
    public List<KnowledgeAreaEntity> getKnowledgeAreasRequested() {
        return knowledgeAreasRequested;
    }

    /**
     * @param knowledgeAreasRequested the knowledgeAreasRequested to set
     */
    public void setKnowledgeAreasRequested(List<KnowledgeAreaEntity> knowledgeAreasRequested) {
        this.knowledgeAreasRequested = knowledgeAreasRequested;
    }

    public List<TranslationOfferEntity> getTranslationOffers() {
        return translationOffers;
    }

    public void setTranslationOffers(List<TranslationOfferEntity> translationOffers) {
        this.translationOffers = translationOffers;
    }
    
}
