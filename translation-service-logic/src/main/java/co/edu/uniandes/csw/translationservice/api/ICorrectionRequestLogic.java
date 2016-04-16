package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import co.edu.uniandes.csw.translationservice.entities.KnowledgeAreaEntity;
import java.util.List;

public interface ICorrectionRequestLogic {
    public int countCorrectionRequests();
    public List<CorrectionRequestEntity> getCorrectionRequests();
    public List<CorrectionRequestEntity> getCorrectionRequests(Integer page, Integer maxRecords);
    public CorrectionRequestEntity getCorrectionRequest(Long id);
    public CorrectionRequestEntity createCorrectionRequest(CorrectionRequestEntity entity);
    public CorrectionRequestEntity updateCorrectionRequest(CorrectionRequestEntity entity);
    public void deleteCorrectionRequest(Long id);
    
    public List<KnowledgeAreaEntity> listKnowledgeAreas(Long id);
    public KnowledgeAreaEntity getKnowledgeAreas(Long id, Long KnowledgeAreaId);
    public KnowledgeAreaEntity addKnowledgeAreas(Long id, Long KnowledgeAreaId);
    public List<KnowledgeAreaEntity> replaceKnowledgeAreas(Long id, List<KnowledgeAreaEntity> list);
    public void removeKnowledgeAreas(Long id, Long KnowledgeAreaId);
}
