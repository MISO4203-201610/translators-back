package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@NamedQueries({
    @NamedQuery(name = "Resume.getByTranslator", query = "select c from ResumeEntity c Where c.translator.id=:id")
}) 
public class ResumeEntity extends BaseEntity implements Serializable {

    private String professionalProfile;
    
    private String achievements;
    
    private String personalInformation;
    
    @PodamExclude
    @ManyToOne
    private TranslatorEntity translator;

    public String getProfessionalProfile() {
        return professionalProfile;
    }

    public void setProfessionalProfile(String professionalProfile) {
        this.professionalProfile = professionalProfile;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(String personalInformation) {
        this.personalInformation = personalInformation;
    }

    public TranslatorEntity getTranslator() {
        return translator;
    }

    public void setTranslator(TranslatorEntity translator) {
        this.translator = translator;
    }

   
}
