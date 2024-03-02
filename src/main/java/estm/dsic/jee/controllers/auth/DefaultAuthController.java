package estm.dsic.jee.controllers.auth;

import estm.dsic.jee.beans.User;
import estm.dsic.jee.controllers.auth.interfaces.IAuthController;
import estm.dsic.jee.dao.user.UserDaoJDBC;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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
