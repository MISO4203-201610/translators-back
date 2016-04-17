package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
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
    public KnowledgeAreaEntity getKnowledgeAreas(Long id, Long knowledgeAreaId);
    public KnowledgeAreaEntity addKnowledgeAreas(Long id, Long knowledgeAreaId);
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long id, List<KnowledgeAreaEntity> list);

    public void removeKnowledgeAreas(Long id, Long knowledgeAreaId);
    
    public List<TranslationOfferEntity> listTranslationOffers(Long id);
    public TranslationOfferEntity getTranslationOffers(Long id, Long translationOfferId);
    public TranslationOfferEntity addTranslationOffers(Long id, Long translationOfferId);
    public List<TranslationOfferEntity> replaceTranslationOffers(Long id, List<TranslationOfferEntity> list);
    public void removeTranslationOffers(Long id, Long translationOfferId);
}
