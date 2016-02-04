package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.translationservice.dtos.ProfileDTO;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentAccount;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentCustomer;
import static co.edu.uniandes.csw.translationservice.services.AccountService.getCurrentTranslator;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

@Path("profile")
public class ProfileService {

    @Context
    private HttpServletRequest req;

    @GET
    public ProfileDTO getProfile() {
        ProfileDTO profile = new ProfileDTO(getCurrentAccount(req.getRemoteUser()));
        try {
            CustomerEntity customer = getCurrentCustomer(req.getRemoteUser());
            profile.setBirthDate(customer.getBirthDate());
            profile.setPicture(customer.getPicture());
        } catch (WebApplicationException e) {
        }
        try {
            TranslatorEntity translator = getCurrentTranslator(req.getRemoteUser());
            profile.setBirthDate(translator.getBirthDate());
            profile.setPicture(translator.getPicture());
        } catch (WebApplicationException e) {
        }
        return profile;
    }
}
