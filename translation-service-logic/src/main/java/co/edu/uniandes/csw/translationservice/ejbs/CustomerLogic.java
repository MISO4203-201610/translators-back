package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.persistence.CustomerPersistence;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.api.ICorrectionRequestLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.api.ITranslationRequestLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class CustomerLogic implements ICustomerLogic {

    @Inject private CustomerPersistence persistence;

    @Inject private ICorrectionRequestLogic correctionRequestLogic;

    @Inject private ITranslationRequestLogic translationRequestLogic;

    /**
     * @generated
     */
    @Override
    public int countCustomers() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<CustomerEntity> getCustomers() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<CustomerEntity> getCustomers(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public CustomerEntity getCustomer(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public CustomerEntity createCustomer(CustomerEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public CustomerEntity updateCustomer(CustomerEntity entity) {
        CustomerEntity newEntity = entity;
        CustomerEntity oldEntity = persistence.find(entity.getId());
        newEntity.setCorrectionRequests(oldEntity.getCorrectionRequests());
        newEntity.setTranslationRequests(oldEntity.getTranslationRequests());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteCustomer(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<CorrectionRequestEntity> listCorrectionRequests(Long customerId) {
        return persistence.find(customerId).getCorrectionRequests();
    }

    /**
     * @generated
     */
    @Override
    public CorrectionRequestEntity getCorrectionRequests(Long customerId, Long correctionRequestsId) {
        List<CorrectionRequestEntity> list = persistence.find(customerId).getCorrectionRequests();
        CorrectionRequestEntity correctionRequestsEntity = new CorrectionRequestEntity();
        correctionRequestsEntity.setId(correctionRequestsId);
        int index = list.indexOf(correctionRequestsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public CorrectionRequestEntity addCorrectionRequests(Long customerId, Long correctionRequestsId) {
        CustomerEntity customerEntity = persistence.find(customerId);
        CorrectionRequestEntity correctionRequestsEntity = correctionRequestLogic.getCorrectionRequest(correctionRequestsId);
        correctionRequestsEntity.setCustomer(customerEntity);
        return correctionRequestsEntity;
    }

    /**
     * @generated
     */
    @Override
    public List<CorrectionRequestEntity> replaceCorrectionRequests(Long customerId, List<CorrectionRequestEntity> list) {
        CustomerEntity customerEntity = persistence.find(customerId);
        List<CorrectionRequestEntity> correctionRequestList = correctionRequestLogic.getCorrectionRequests();
        for (CorrectionRequestEntity correctionRequest : correctionRequestList) {
            if (list.contains(correctionRequest)) {
                correctionRequest.setCustomer(customerEntity);
            } else {
                if (correctionRequest.getCustomer() != null && correctionRequest.getCustomer().equals(customerEntity)) {
                    correctionRequest.setCustomer(null);
                }
            }
        }
        customerEntity.setCorrectionRequests(list);
        return customerEntity.getCorrectionRequests();
    }

    /**
     * @generated
     */
    @Override
    public void removeCorrectionRequests(Long customerId, Long correctionRequestsId) {
        CorrectionRequestEntity entity = correctionRequestLogic.getCorrectionRequest(correctionRequestsId);
        entity.setCustomer(null);
    }

    /**
     * @generated
     */
    @Override
    public List<TranslationRequestEntity> listTranslationRequests(Long customerId) {
        return persistence.find(customerId).getTranslationRequests();
    }

    /**
     * @generated
     */
    @Override
    public TranslationRequestEntity getTranslationRequests(Long customerId, Long translationRequestsId) {
        List<TranslationRequestEntity> list = persistence.find(customerId).getTranslationRequests();
        TranslationRequestEntity translationRequestsEntity = new TranslationRequestEntity();
        translationRequestsEntity.setId(translationRequestsId);
        int index = list.indexOf(translationRequestsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public TranslationRequestEntity addTranslationRequests(Long customerId, Long translationRequestsId) {
        CustomerEntity customerEntity = persistence.find(customerId);
        TranslationRequestEntity translationRequestsEntity = translationRequestLogic.getTranslationRequest(translationRequestsId);
        translationRequestsEntity.setCustomer(customerEntity);
        return translationRequestsEntity;
    }

    /**
     * @generated
     */
    @Override
    public List<TranslationRequestEntity> replaceTranslationRequests(Long customerId, List<TranslationRequestEntity> list) {
        CustomerEntity customerEntity = persistence.find(customerId);
        List<TranslationRequestEntity> translationRequestList = translationRequestLogic.getTranslationRequests();
        for (TranslationRequestEntity translationRequest : translationRequestList) {
            if (list.contains(translationRequest)) {
                translationRequest.setCustomer(customerEntity);
            } else {
                if (translationRequest.getCustomer() != null && translationRequest.getCustomer().equals(customerEntity)) {
                    translationRequest.setCustomer(null);
                }
            }
        }
        customerEntity.setTranslationRequests(list);
        return customerEntity.getTranslationRequests();
    }

    /**
     * @generated
     */
    @Override
    public void removeTranslationRequests(Long customerId, Long translationRequestsId) {
        TranslationRequestEntity entity = translationRequestLogic.getTranslationRequest(translationRequestsId);
        entity.setCustomer(null);
    }
}
