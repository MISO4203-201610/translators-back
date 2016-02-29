/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.dtos;

/**
 *
 * @author af.esguerra10
 */
public class ReviewDTO {

    private Long id;

    private String name;

    private String source;

    private String description;

    private TranslatorDTO translator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TranslatorDTO getTranslator() {
        return translator;
    }

    public void setTranslator(TranslatorDTO translator) {
        this.translator = translator;
    }
}
