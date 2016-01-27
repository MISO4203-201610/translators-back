package co.edu.uniandes.csw.translationservice.api;

import co.edu.uniandes.csw.translationservice.entities.CorrectionRequestEntity;
import java.util.List;

public interface ICorrectionRequestLogic {
    public int countCorrectionRequests();
    public List<CorrectionRequestEntity> getCorrectionRequests();
    public List<CorrectionRequestEntity> getCorrectionRequests(Integer page, Integer maxRecords);
    public CorrectionRequestEntity getCorrectionRequest(Long id);
    public CorrectionRequestEntity createCorrectionRequest(CorrectionRequestEntity entity);
    public CorrectionRequestEntity updateCorrectionRequest(CorrectionRequestEntity entity);
    public void deleteCorrectionRequest(Long id);
}
