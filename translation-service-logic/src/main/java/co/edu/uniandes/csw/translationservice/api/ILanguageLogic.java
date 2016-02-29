package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.LanguageEntity;
import java.util.List;

public interface ILanguageLogic {
    public int countLanguages();
    public List<LanguageEntity> getLanguages();
    public List<LanguageEntity> getLanguages(Integer page, Integer maxRecords);
    public LanguageEntity getLanguage(Long id);
    public LanguageEntity createLanguage(LanguageEntity entity);
    public LanguageEntity updateLanguage(LanguageEntity entity);
    public void deleteLanguage(Long id);
}
