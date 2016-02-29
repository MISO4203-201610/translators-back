package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import java.util.List;

public interface ITranslatorLogic {
    public int countTranslators();
    public List<TranslatorEntity> getTranslators();
    public List<TranslatorEntity> getTranslators(Integer page, Integer maxRecords);
    public TranslatorEntity getTranslator(Long id);
    public TranslatorEntity createTranslator(TranslatorEntity entity);
    public TranslatorEntity updateTranslator(TranslatorEntity entity);
    public void deleteTranslator(Long id);
    public List<LanguageEntity> listLanguages(Long translatorId);
    public LanguageEntity getLanguages(Long translatorId, Long languagesId);
    public LanguageEntity addLanguages(Long translatorId, Long languagesId);
    public List<LanguageEntity> replaceLanguages(Long translatorId, List<LanguageEntity> list);
    public void removeLanguages(Long translatorId, Long languagesId);
}
