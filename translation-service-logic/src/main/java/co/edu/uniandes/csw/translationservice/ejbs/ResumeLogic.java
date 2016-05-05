/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.IResumeLogic;
import co.edu.uniandes.csw.translationservice.entities.ResumeEntity;
import co.edu.uniandes.csw.translationservice.persistence.ResumePersistence;
import java.util.List;
import javax.inject.Inject;

public class ResumeLogic implements IResumeLogic {
    
    @Inject private ResumePersistence persistence;

    @Override
    public int countResumes() {
        return persistence.count();
    }

    @Override
    public List<ResumeEntity> getResumes() {
        return persistence.findAll();
    }

    @Override
    public List<ResumeEntity> getResumes(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public ResumeEntity getResume(Long id) {
        return persistence.find(id);
    }

    @Override
    public ResumeEntity createResume(ResumeEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ResumeEntity updateResume(ResumeEntity entity) {
        ResumeEntity newEntity = entity;
        ResumeEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteResume(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<ResumeEntity> getByTranslator(Long id) {
        return persistence.getByTranslator(id);
    }
}