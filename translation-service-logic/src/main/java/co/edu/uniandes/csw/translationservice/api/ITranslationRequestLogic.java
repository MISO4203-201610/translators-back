package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.TranslationRequestEntity;
import java.util.List;

public interface ITranslationRequestLogic {
    public int countTranslationRequests();
    public List<TranslationRequestEntity> getTranslationRequests();
    public List<TranslationRequestEntity> getTranslationRequests(Integer page, Integer maxRecords);
    public TranslationRequestEntity getTranslationRequest(Long id);
    public TranslationRequestEntity createTranslationRequest(TranslationRequestEntity entity);
    public TranslationRequestEntity updateTranslationRequest(TranslationRequestEntity entity);
    public void deleteTranslationRequest(Long id);
}
