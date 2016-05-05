package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.ITranslatorOfertLogic;
import co.edu.uniandes.csw.translationservice.entities.TranslatorOfertEntity;
import co.edu.uniandes.csw.translationservice.persistence.TranslatorOfertPersistence;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *  @author Germán
 */
@Stateless
public class TranslatorOfertLogic implements ITranslatorOfertLogic {

    @Inject private TranslatorOfertPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countTranslatorOferts() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorOfertEntity> getTranslatorOferts() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<TranslatorOfertEntity> getTranslatorOferts(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity getTranslatorOfert(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity createTranslatorOfert(TranslatorOfertEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public TranslatorOfertEntity updateTranslatorOfert(TranslatorOfertEntity entity) {
        TranslatorOfertEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteTranslatorOfert(Long id) {
        persistence.delete(id);
    }
    
    @Override
    public TranslatorOfertEntity setEnlaceArchivoResultado(Long id, String enlace)
    {
        TranslatorOfertEntity oferta = this.getTranslatorOfert(id);
        oferta.getTranslationRequest().setEnlaceArchivoResultado(enlace);
        return persistence.update(oferta);
    }
    
    @Override
    public List<TranslatorOfertEntity> getAcceptedOferts(Long translatorId)
    {
        List<TranslatorOfertEntity> lista = persistence.findAll();
        List<TranslatorOfertEntity> respuesta = new LinkedList<TranslatorOfertEntity>();
        for (int i = 0; i < lista.size(); i++) {
            if(Objects.equals(lista.get(i).getTranslator().getId(), translatorId) && lista.get(i).isAcceptedByCustomer())
                respuesta.add(lista.get(i));
        }
        return respuesta;
    }

}
