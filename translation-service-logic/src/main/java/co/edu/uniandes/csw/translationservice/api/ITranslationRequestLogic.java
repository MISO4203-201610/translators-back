package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
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
    
    public List<KnowledgeAreaEntity> listKnowledgeAreas(Long id);
    public KnowledgeAreaEntity getKnowledgeAreas(Long id, Long KnowledgeAreaId);
    public KnowledgeAreaEntity addKnowledgeAreas(Long id, Long KnowledgeAreaId);
    public CustomerEntity getCustomer(Long id);
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long id, List<KnowledgeAreaEntity> list);
    public void removeKnowledgeAreas(Long id, Long KnowledgeAreaId);
    
    public List<TranslatorOfertEntity> listTranslatorOferts(Long id);
    public TranslatorOfertEntity getTranslatorOferts(Long id, Long translatorOfertId);
    public TranslatorOfertEntity addTranslatorOferts(Long id, Long translatorOfertId);
    public List<TranslatorOfertEntity> replaceTranslatorOferts(Long id, List<TranslatorOfertEntity> list);
    public void removeTranslatorOferts(Long id, Long TranslatorOfertId);
}
