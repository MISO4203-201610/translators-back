package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@Entity
public class KnowledgeAreaEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany
    private List<TranslationRequestEntity> TraslationRequested = new ArrayList<>();

    /**
     * @return the TraslationRequested
     */
    public List<TranslationRequestEntity> getTraslationRequested() {
        return TraslationRequested;
    }

    /**
     * @param TraslationRequested the TraslationRequested to set
     */
    public void setTraslationRequested(List<TranslationRequestEntity> TraslationRequested) {
        this.TraslationRequested = TraslationRequested;
    }

    
}
