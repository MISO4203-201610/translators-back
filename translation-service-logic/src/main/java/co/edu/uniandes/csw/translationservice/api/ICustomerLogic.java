package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import java.util.List;

public interface ICustomerLogic {
    public int countCustomers();
    public List<CustomerEntity> getCustomers();
    public List<CustomerEntity> getCustomers(Integer page, Integer maxRecords);
    public CustomerEntity getCustomer(Long id);
    public CustomerEntity createCustomer(CustomerEntity entity);
    public CustomerEntity updateCustomer(CustomerEntity entity);
    public void deleteCustomer(Long id);
    public List<TranslationRequestEntity> listTranslationRequests(Long customerId);
    public TranslationRequestEntity getTranslationRequests(Long customerId, Long translationRequestsId);
    public TranslationRequestEntity addTranslationRequests(Long customerId, Long translationRequestsId);
    public List<TranslationRequestEntity> replaceTranslationRequests(Long customerId, List<TranslationRequestEntity> list);
    public void removeTranslationRequests(Long customerId, Long translationRequestsId);
    public List<CorrectionRequestEntity> listCorrectionRequests(Long customerId);
    public CorrectionRequestEntity getCorrectionRequests(Long customerId, Long correctionRequestsId);
    public CorrectionRequestEntity addCorrectionRequests(Long customerId, Long correctionRequestsId);
    public List<CorrectionRequestEntity> replaceCorrectionRequests(Long customerId, List<CorrectionRequestEntity> list);
    public void removeCorrectionRequests(Long customerId, Long correctionRequestsId);
}
