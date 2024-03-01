package estm.dsic.jee.controllers.auth.interfaces;

import estm.dsic.jee.beans.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;

public interface IAuthController extends Serializable {
    User authenticate(User u);
    User register(User user);
}
