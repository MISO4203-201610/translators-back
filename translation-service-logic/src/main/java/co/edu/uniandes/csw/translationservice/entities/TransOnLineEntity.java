/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author juan camilo cerquera <jc.cerquera10@uniandes.edu.co>
 */
@Entity
public class TransOnLineEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;

    @PodamExclude
    @ManyToOne
    private CustomerEntity customer;

    @PodamExclude
    @ManyToOne
    private TranslatorEntity contractor;

    @PodamExclude
    @OneToMany(mappedBy = "chatName")
    private List<TransOnLineMsgEntity> chatMsgs = new ArrayList<>();

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the customer
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * @return the contractor
     */
    public TranslatorEntity getContractor() {
        return contractor;
    }

    /**
     * @param contractor the contractor to set
     */
    public void setContractor(TranslatorEntity contractor) {
        this.contractor = contractor;
    }

    /**
     * @return the chatMsgsTransOnLineTest
     */
    public List<TransOnLineMsgEntity> getChatMsgs() {
        return chatMsgs;
    }

    /**
     * @param chatMsgs the chatMsgs to set
     */
    public void setChatMsgs(List<TransOnLineMsgEntity> chatMsgs) {
        this.chatMsgs = chatMsgs;
    }

}
