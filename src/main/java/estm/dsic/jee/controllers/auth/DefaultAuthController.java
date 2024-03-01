package estm.dsic.jee.controllers.auth;

import estm.dsic.jee.controllers.auth.interfaces.IAuthController;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.dao.user.UserDaoJDBC;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Named
@SessionScoped
public class DefaultAuthController implements IAuthController {
    @Inject
    private UserDaoJDBC userDoa;

    @Override
    public User authenticate(User u) {
        User user = userDoa.get(u.getEmail(), u.getPassword());
        return user;
    }

    @Override
    public User register(User user) {
        User newUser = userDoa.create(user);
        return newUser;
    }
}
