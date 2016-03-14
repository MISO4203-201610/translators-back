/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
import java.util.List;

/**
 *
 * @author Germán
 */
public interface ITranslatorOfertLogic {
    public int countTranslatorOferts();
    public List<TranslatorOfertEntity> getTranslatorOferts();
    public List<TranslatorOfertEntity> getTranslatorOferts(Integer page, Integer maxRecords);
    public TranslatorOfertEntity getTranslatorOfert(Long id);
    public TranslatorOfertEntity createTranslatorOfert(TranslatorOfertEntity entity);
    public TranslatorOfertEntity updateTranslatorOfert(TranslatorOfertEntity entity);
    public void deleteTranslatorOfert(Long id);
}
