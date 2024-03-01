package estm.dsic.jee.business.interfaces;

import estm.dsic.jee.beans.User;

public interface IAdminServices {
    User update(User user);
    User delete(User user);

}
