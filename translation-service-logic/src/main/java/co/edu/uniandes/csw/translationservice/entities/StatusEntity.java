package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;

/**
 * @generated
 */
@Entity
public class StatusEntity extends BaseEntity implements Serializable {


    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((StatusEntity)obj).getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
