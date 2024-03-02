package estm.dsic.jee.business.interfaces;

import estm.dsic.jee.beans.User;
import jakarta.ws.rs.core.Response;

public interface IAdminServices {
    Response verifyUser(User user);
    Response delete(User user);
    Response getAllUsers();

}
