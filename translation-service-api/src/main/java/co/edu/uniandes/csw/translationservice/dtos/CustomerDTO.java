package co.edu.uniandes.csw.translationservice.dtos;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * @generated
 */
@XmlRootElement
public class CustomerDTO {

    @PodamExclude
    private List<TranslationRequestDTO> translationRequests = new ArrayList<>();
    @PodamExclude
    private List<CorrectionRequestDTO> correctionRequests = new ArrayList<>();
    
    private Long id;
    private String name;
    private String picture;
   
    @PodamStrategyValue(DateStrategy.class)
    private Date birthDate;

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
    public String getPicture() {
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @generated
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @generated
     */
    public void setBirthDate(Date birthdate) {
        this.birthDate = birthdate;
    }

    /**
     * @generated
     */
    public List<TranslationRequestDTO> getTranslationRequests() {
        return translationRequests;
    }

    /**
     * @generated
     */
    public void setTranslationRequests(List<TranslationRequestDTO> translationrequests) {
        this.translationRequests = translationrequests;
    }

    /**
     * @generated
     */
    public List<CorrectionRequestDTO> getCorrectionRequests() {
        return correctionRequests;
    }

    /**
     * @generated
     */
    public void setCorrectionRequests(List<CorrectionRequestDTO> correctionrequests) {
        this.correctionRequests = correctionrequests;
    }

}
