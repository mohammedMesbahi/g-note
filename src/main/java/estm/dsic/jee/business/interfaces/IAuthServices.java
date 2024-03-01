package estm.dsic.jee.business.interfaces;

import estm.dsic.jee.beans.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

public interface IAuthServices {
    Response authenticate(User u);
    Response register(User user);
    Response logout(HttpServletResponse response);

}
