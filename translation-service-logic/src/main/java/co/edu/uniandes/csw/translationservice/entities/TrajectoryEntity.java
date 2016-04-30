/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Trajectory.getByTranslator", 
            query = "select u from TrajectoryEntity u Where u.translator.id=:id"),
}) 
public class TrajectoryEntity extends BaseEntity implements Serializable{
    
    private String projectName;
    
    private String description;
    
    private String duration;
    
    private String company;
    
    @PodamExclude
    @ManyToOne
    private TranslatorEntity translator;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public TranslatorEntity getTranslator() {
        return translator;
    }

    public void setTranslator(TranslatorEntity translator) {
        this.translator = translator;
    }
    
}
