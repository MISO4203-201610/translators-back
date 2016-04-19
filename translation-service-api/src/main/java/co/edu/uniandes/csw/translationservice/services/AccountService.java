package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.service.AuthService;
import static co.edu.uniandes.csw.auth.stormpath.Utils.getClient;
import co.edu.uniandes.csw.translationservice.api.ICustomerLogic;
import co.edu.uniandes.csw.translationservice.api.ITranslatorLogic;
import co.edu.uniandes.csw.translationservice.entities.CustomerEntity;
import co.edu.uniandes.csw.translationservice.entities.TranslatorEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.resource.ResourceException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;

public class AccountService extends AuthService {

    private static final String CUSTOMER_GROUP_HREF = "https://api.stormpath.com/v1/groups/5EWhrKIYWtDBCdO4u1bUgY";
    private static final String TRANSLATOR_GROUP_HREF = "https://api.stormpath.com/v1/groups/5UP7pBTeTzylso9xmxWaPW";
    public static final String CUSTOMER_CUSTOM_DATA_KEY = "customerId";
    public static final String TRANSLATOR_CUSTOM_DATA_KEY = "translatorId";

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
                        translator.setEmail(acc.getEmail());
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

    public static CustomerEntity getCurrentCustomer(String href) {
        Account account = getCurrentAccount(href);
        Integer customerId = (Integer) account.getCustomData().get(CUSTOMER_CUSTOM_DATA_KEY);
        if (customerId == null) {
            throw new WebApplicationException(HttpServletResponse.SC_FORBIDDEN);
        }
        CustomerEntity customer = new CustomerEntity();
        customer.setId(new Long(customerId));
        return customer;
    }

    public static TranslatorEntity getCurrentTranslator(String href) {
        Account account = getCurrentAccount(href);
        Integer translatorId = (Integer) account.getCustomData().get(TRANSLATOR_CUSTOM_DATA_KEY);
        if (translatorId == null) {
            throw new WebApplicationException(HttpServletResponse.SC_FORBIDDEN);
        }
        TranslatorEntity translator = new TranslatorEntity();
        translator.setId(new Long(translatorId));
        return translator;
    }

    public static Account getCurrentAccount(String href) {
        if (href == null) {
            throw new WebApplicationException(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getClient().getResource(href, Account.class);
    }
}
