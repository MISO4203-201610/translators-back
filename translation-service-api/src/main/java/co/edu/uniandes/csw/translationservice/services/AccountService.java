package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.service.AuthService;
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.resource.ResourceException;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

public class AccountService extends AuthService {

    private final String CUSTOMER_GROUP_HREF = "https://api.stormpath.com/v1/groups/6Sx6sEZ85WJtoFDza3R9GD";
    private final String TRANSLATOR_GROUP_HREF = "https://api.stormpath.com/v1/groups/6rX3H3p1Ig7nAJOQDvBHDd";
    private final String CUSTOMER_CUSTOM_DATA_KEY = "customerId";
    private final String TRANSLATOR_CUSTOM_DATA_KEY = "translatorId";

    @Inject
    private ICustomerLogic customerLogic;

    @Inject
    private ITranslatorLogic translatorLogic;

    @Override
    public void register(UserDTO udto) {
        try {
            Account acc = createUser(udto);
            for (Group group : acc.getGroups()) {
                switch (group.getHref()) {
                    case CUSTOMER_GROUP_HREF:
                        CustomerEntity customer = new CustomerEntity();
                        customer.setName(acc.getFullName());
                        customer = customerLogic.createCustomer(customer);
                        acc.getCustomData().put(CUSTOMER_CUSTOM_DATA_KEY, customer.getId());
                        break;
                    case TRANSLATOR_GROUP_HREF:
                        TranslatorEntity translator = new TranslatorEntity();
                        translator.setName(acc.getFullName());
                        translator = translatorLogic.createTranslator(translator);
                        acc.getCustomData().put(TRANSLATOR_CUSTOM_DATA_KEY, translator.getId());
                        break;
                }
            }
            acc.getCustomData().save();
        } catch (ResourceException e) {
            throw new WebApplicationException(e, e.getStatus());
        }
    }

}
