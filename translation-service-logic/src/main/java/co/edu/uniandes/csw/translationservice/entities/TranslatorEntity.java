package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;

/**
 * @generated
 */
@Entity
public class TranslatorEntity extends BaseEntity implements Serializable {

    
    @OneToMany(mappedBy = "translator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationEntity> education = new ArrayList<>();

    @PodamExclude
    @OneToMany
    private List<LanguageEntity> languages = new ArrayList<>();

    /**
     * @generated
     */
    public List<EducationEntity> getEducation() {
        return education;
    }

    /**
     * @generated
     */
    public void setEducation(List<EducationEntity> education) {
        this.education = education;
    }

    /**
     * @generated
     */
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    /**
     * @generated
     */
    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }


    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((TranslatorEntity)obj).getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
