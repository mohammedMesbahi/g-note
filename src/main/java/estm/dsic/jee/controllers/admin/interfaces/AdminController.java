package estm.dsic.jee.controllers.admin.interfaces;

import estm.dsic.jee.beans.User;

public interface AdminController {
    User verifiyUser(User user);
    User removeUser(User user);
}
