/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.dtos;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author l.badillo10
 */
@XmlRootElement
public class TransOnLineDTO {
    private Long id;
    private String name;
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;
    private Long idCustomer;
    private Long idTranslator;
    private List<TransOnLineMsgDTO> listChatMsg = new ArrayList<>();
    
    
    

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * @return the idCustomer
     */
    public Long getIdCustomer() {
        return idCustomer;
    }

    /**
     * @param idCustomer the idCustomer to set
     */
    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * @return the listChatMsg
     */
    public List<TransOnLineMsgDTO> getListChatMsg() {
        return listChatMsg;
    }

    /**
     * @param listChatMsg the listChatMsg to set
     */
    public void setListChatMsg(List<TransOnLineMsgDTO> listChatMsg) {
        this.listChatMsg = listChatMsg;
    }

    public Long getIdTranslator() {
        return idTranslator;
    }

    public void setIdTranslator(Long idTranslator) {
        this.idTranslator = idTranslator;
    }
    
}
