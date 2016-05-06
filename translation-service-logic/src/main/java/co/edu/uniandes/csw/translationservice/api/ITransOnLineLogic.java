/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.TransOnLineEntity;
import java.util.List;

/**
 *
 * @author juan camilo cerquera <jc.cerquera10@uniandes.edu.co>
 */
public interface ITransOnLineLogic {
    public int countTransOnLines();
    public List<TransOnLineEntity> getTransOnLines();
    public List<TransOnLineEntity> getTransOnLines(Integer page, Integer maxRecords);
    public TransOnLineEntity getTransOnLine(String description);
    public TransOnLineEntity createTransOnLine(TransOnLineEntity entity);
    public TransOnLineEntity updateTransOnLine(TransOnLineEntity entity);
    public void deleteTransOnLine(Long id);
    
}
