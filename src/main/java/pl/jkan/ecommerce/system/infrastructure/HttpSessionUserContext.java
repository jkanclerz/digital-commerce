package pl.jkan.ecommerce.system.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.system.SystemUser;
import pl.jkan.ecommerce.system.SystemUserContext;

import javax.servlet.http.HttpSession;

public class HttpSessionUserContext implements SystemUserContext {

    private static final String USER_KEY = "system_user";
    private HttpSession session;

    public HttpSessionUserContext(HttpSession session) {
        this.session = session;
    }

    @Override
    public SystemUser getCurrentUser() {
        if (session.getAttribute(USER_KEY) == null) {
            session.setAttribute(USER_KEY, Identifier.generateUUID().toString());
        }

        return new SystemUser(new Identifier(session.getAttribute(USER_KEY).toString()));
    }
}
