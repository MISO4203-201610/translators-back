/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author juan camilo cerquera lozada<jc.cerquera10@uniandes.edu.co>
 */
@Entity

public class TransOnLineMsgEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private TransOnLineEntity chatName;
    
    private String userName;
    private String userMesg;

    /**
     * @return the chatName
     */
    public TransOnLineEntity getChatName() {
        return chatName;
    }

    /**
     * @param chatName the chatName to set
     */
    public void setChatName(TransOnLineEntity chatName) {
        this.chatName = chatName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userMesg
     */
    public String getUserMesg() {
        return userMesg;
    }

    /**
     * @param userMesg the userMesg to set
     */
    public void setUserMesg(String userMesg) {
        this.userMesg = userMesg;
    }
    
    
    
    
}
