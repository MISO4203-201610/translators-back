/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.TransOnLineMsgEntity;
import java.util.List;

/**
 *
 * @author juan camilo cerquera <jc.cerquera10@uniandes.edu.co>
 */
public interface ITransOnLineMsgLogic {
    
   
    public int countTransOnLineMsgs();
    public List<TransOnLineMsgEntity> getTransOnLineMsgs();
    public List<TransOnLineMsgEntity> getTransOnLineMsgs(Integer page, Integer maxRecords);
    public TransOnLineMsgEntity getTransOnLineMsg(Long id);
    public TransOnLineMsgEntity createTransOnLineMsg(TransOnLineMsgEntity entity);
    public TransOnLineMsgEntity updateTransOnLineMsg(TransOnLineMsgEntity entity);
    public void deleteTransOnLineMsg(Long id);
}
   
