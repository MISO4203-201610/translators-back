package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import java.util.List;

public interface IKnowledgeAreaLogic {
    public int countKnowledgeAreas();
    public List<KnowledgeAreaEntity> getKnowledgeAreas();
    public List<KnowledgeAreaEntity> getKnowledgeAreas(Integer page, Integer maxRecords);
    public KnowledgeAreaEntity getKnowledgeArea(Long id);
    public KnowledgeAreaEntity createKnowledgeArea(KnowledgeAreaEntity entity);
    public KnowledgeAreaEntity updateKnowledgeArea(KnowledgeAreaEntity entity);
    public void deleteKnowledgeArea(Long id);
}