/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.InvitationEntity;
import java.util.List;

/**
 *
 * @author e.soto162
 */
public interface IInvitationLogic {
    
    public List<InvitationEntity> getInvitationList();
    public InvitationEntity getInvitation(Long invitationId);
    public int countInvitations();
    public InvitationEntity createInvitation(InvitationEntity invitation);
    public InvitationEntity updateInvitation(InvitationEntity invitation);
    public void deleteInvitation(Long invitationId);
    public List<InvitationEntity> getInvitations();
    
}
