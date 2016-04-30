/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.api;
import co.edu.uniandes.csw.translationservice.entities.TrajectoryEntity;
import java.util.List;

public interface ITrajectoryLogic {
    
    public int countTrajectory();
    public List<TrajectoryEntity> getTrajectory();
    public List<TrajectoryEntity> getTrajectory(Integer page, Integer maxRecords);
    public TrajectoryEntity getTrajectory(Long id);
    public TrajectoryEntity createTrajectory(TrajectoryEntity entity);
    public TrajectoryEntity updateTrajectory(TrajectoryEntity entity);
    public void deleteTrajectory(Long id);
    public List<TrajectoryEntity> getByTranslator(Long id);
    
}
