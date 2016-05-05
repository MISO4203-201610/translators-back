/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.ResumeEntity;
import java.util.List;

public interface IResumeLogic {
    public int countResumes();
    public List<ResumeEntity> getResumes();
    public List<ResumeEntity> getResumes(Integer page, Integer maxRecords);
    public ResumeEntity getResume(Long id);
    public ResumeEntity createResume(ResumeEntity entity);
    public ResumeEntity updateResume(ResumeEntity entity);
    public void deleteResume(Long id);
    public List<ResumeEntity> getByTranslator(Long id);
}
