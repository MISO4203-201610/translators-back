package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;

/**
 * @generated
 */
@XmlRootElement
public class PersonDTO {

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
}
