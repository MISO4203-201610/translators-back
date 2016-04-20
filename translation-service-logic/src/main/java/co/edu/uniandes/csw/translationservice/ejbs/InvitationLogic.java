/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.IInvitationLogic;
import co.edu.uniandes.csw.translationservice.entities.InvitationEntity;
import co.edu.uniandes.csw.translationservice.persistence.InvitationPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author e.soto162
 */
@Stateless
public class InvitationLogic implements IInvitationLogic{

    @Inject private InvitationPersistence persistence;
    
    @Override
    public List<InvitationEntity> getInvitationList() {
        return persistence.findAll();
    }

    @Override
    public InvitationEntity getInvitation(Long invitationId) {
        return persistence.find(invitationId);
    }

    @Override
    public int countInvitations() {
        return persistence.count();
    }

    @Override
    public InvitationEntity createInvitation(InvitationEntity invitation) {
        return persistence.create(invitation);
    }

    @Override
    public InvitationEntity updateInvitation(InvitationEntity invitation) {
        return persistence.update(invitation);
    }

    @Override
    public void deleteInvitation(Long invitationId) {
        persistence.delete(invitationId);
    }

    @Override
    public List<InvitationEntity> getInvitations() {
        return persistence.findAll();
    }
    
}
