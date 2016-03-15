package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
import java.util.List;

public interface ITranslatorLogic {
    public int countTranslators();
    public List<TranslatorEntity> getTranslators();
    public List<TranslatorEntity> getTranslators(Integer page, Integer maxRecords);
    public TranslatorEntity getTranslator(Long id);
    public TranslatorEntity createTranslator(TranslatorEntity entity);
    public TranslatorEntity updateTranslator(TranslatorEntity entity);
    public void deleteTranslator(Long id);
    public List<TranslatorOfertEntity> listTranslatorOferts(Long translatorId);
    public TranslatorOfertEntity getTranslatorOferts(Long translatorId, Long translatorOfertsId);
    public TranslatorOfertEntity addTranslatorOferts(Long translatorId, Long translatorOfertsId);
    public List<TranslatorOfertEntity> replaceTranslatorOferts(Long translatorId, List<TranslatorOfertEntity> list);
    public void removeTranslatorOferts(Long translatorId, Long translatorOfertsId);
    public List<LanguageEntity> listLanguages(Long translatorId);
    public LanguageEntity getLanguages(Long translatorId, Long languagesId);
    public LanguageEntity addLanguages(Long translatorId, Long languagesId);
    public List<LanguageEntity> replaceLanguages(Long translatorId, List<LanguageEntity> list);
    public void removeLanguages(Long translatorId, Long languagesId);
    public List<KnowledgeAreaEntity> listKnowledgeAreas(Long translatorId);
    public KnowledgeAreaEntity getKnowledgeAreas(Long translatorId, Long knowledgeAreasId);
    public KnowledgeAreaEntity addKnowledgeAreas(Long translatorId, Long knowledgeAreasId);
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long translatorId, List<KnowledgeAreaEntity> list);
    public void removeKnowledgeAreas(Long translatorId, Long knowledgeAreasId);
}
