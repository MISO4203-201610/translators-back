/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITrajectoryLogic;
import co.edu.uniandes.csw.translationservice.entities.TrajectoryEntity;
import co.edu.uniandes.csw.translationservice.persistence.TrajectoryPersistence;
import java.util.List;
import javax.inject.Inject;

public class TrajectoryLogic implements ITrajectoryLogic {
    
    @Inject private TrajectoryPersistence persistence;

    @Override
    public int countTrajectory() {
        return persistence.count();
    }

    @Override
    public List<TrajectoryEntity> getTrajectory() {
        return persistence.findAll();
    }

    @Override
    public List<TrajectoryEntity> getTrajectory(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public TrajectoryEntity getTrajectory(Long id) {
        return persistence.find(id);
    }

    @Override
    public TrajectoryEntity createTrajectory(TrajectoryEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public TrajectoryEntity updateTrajectory(TrajectoryEntity entity) {
        TrajectoryEntity newEntity = entity;
        TrajectoryEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteTrajectory(Long id) {
        persistence.delete(id);
    }
    
    @Override
    public List<TrajectoryEntity> getByTranslator(Long id) {
        return persistence.getByTranslator(id);
    }
    
}
