/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.TranslationOfferEntity;
import java.util.List;

/**
 *
 * @author Germán
 */
public interface ITranslationOfferLogic {
    public int countTranslationOffers();
    public List<TranslationOfferEntity> getTranslationOffers();
    public List<TranslationOfferEntity> getTranslationOffers(Integer page, Integer maxRecords);
    public TranslationOfferEntity getTranslationOffer(Long id);
    public TranslationOfferEntity createTranslationOffer(TranslationOfferEntity entity);
    public TranslationOfferEntity updateTranslationOffer(TranslationOfferEntity entity);
    public void deleteTranslationOffer(Long id);
}
