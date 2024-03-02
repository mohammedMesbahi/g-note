package estm.dsic.jee.controllers.admin.interfaces;

import estm.dsic.jee.beans.User;

import java.io.Serializable;
import java.util.Vector;

public interface AdminController extends Serializable {
    User verifyUser(User user);
    User removeUser(User user);

    Vector<User> getAllUsers();
}
