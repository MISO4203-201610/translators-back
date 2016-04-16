package co.edu.uniandes.csw.translationservice.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class CorrectionRequestDTO extends RequestDTO {

    @PodamExclude
    private LanguageDTO language;
    
    private String desctiption;
    
    private int numberOfWords;
    
    @PodamExclude
    private List<KnowledgeAreaDTO> knowledgeAreas = new ArrayList<>();

    /**
     * @generated
     */
    public LanguageDTO getLanguage() {
        return language;
    }

    /**
     * @generated
     */
    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }
    
    /**
     * @return the knowledgeAreas
     */
    public List<KnowledgeAreaDTO> getKnowledgeAreas() {
        return knowledgeAreas;
    }

    /**
     * @param knowledgeAreas the knowledgeAreas to set
     */
    public void setKnowledgeAreas(List<KnowledgeAreaDTO> knowledgeAreas) {
        this.knowledgeAreas = knowledgeAreas;
    }
    
}
