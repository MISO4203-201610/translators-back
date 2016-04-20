/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author e.soto162
 */
@XmlRootElement
public class InvitationDTO {
    
    private Long id;
    private TranslatorOfertDTO offer;
    private TranslatorDTO translator;
    private boolean accepted;

    public InvitationDTO() {
    }

    public Long getId() {
        return id;
    }

    public TranslatorOfertDTO getTranslatorOfert() {
        return offer;
    }

    public TranslatorDTO getTranslator() {
        return translator;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTranslatorOfert(TranslatorOfertDTO offer) {
        this.offer = offer;
    }

    public void setTranslator(TranslatorDTO translator) {
        this.translator = translator;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }    
    
}
