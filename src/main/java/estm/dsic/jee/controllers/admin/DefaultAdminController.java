package estm.dsic.jee.controllers.admin;

import estm.dsic.jee.beans.User;
import estm.dsic.jee.controllers.admin.interfaces.AdminController;
import estm.dsic.jee.dao.user.UserDaoJDBC;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Vector;

@Named
@SessionScoped
public class DefaultAdminController implements AdminController {
    @Inject
    UserDaoJDBC userDaoJDBC;
    @Override
    public User verifyUser(User user) {
        return userDaoJDBC.verify(user);
    }

    @Override
    public User removeUser(User user) {
        return userDaoJDBC.delete(user);
    }

    @Override
    public Vector<User> getAllUsers() {
        return userDaoJDBC.getAll();
    }

}
