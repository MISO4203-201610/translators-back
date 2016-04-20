/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.entities;

/**
 *
 * @author e.soto162
 */
import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import javax.persistence.Id;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

@Entity
public class InvitationEntity {

    @Id
    private Long id;
    
    @PodamExclude
    @ManyToOne
    private TranslatorOfertEntity translatorOfert;
    
    @PodamExclude
    @ManyToOne
    private TranslatorEntity translator;
    
    private boolean accepted;

    public InvitationEntity() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public TranslatorOfertEntity getTranslatorOfert() {
        return translatorOfert;
    }

    public void setTranslatorOfert(TranslatorOfertEntity translatorOfert) {
        this.translatorOfert = translatorOfert;
    }

    public TranslatorEntity getTranslator() {
        return translator;
    }

    public void setTranslator(TranslatorEntity translator) {
        this.translator = translator;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
 
}
