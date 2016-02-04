package co.edu.uniandes.csw.translationservice.dtos;

import co.edu.uniandes.csw.auth.model.DateAdapter;
import co.edu.uniandes.csw.auth.model.UserDTO;
import com.stormpath.sdk.account.Account;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class ProfileDTO extends UserDTO {

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDate;

    private String picture;

    public ProfileDTO() {
    }

    public ProfileDTO(Account account) {
        super(account);
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
