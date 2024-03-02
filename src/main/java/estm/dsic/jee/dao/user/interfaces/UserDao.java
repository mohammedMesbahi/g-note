package estm.dsic.jee.dao.user.interfaces;


import estm.dsic.jee.beans.User;

public interface UserDao extends estm.dsic.jee.dao.user.interfaces.CrudAble<User, Integer> {
    User get(User user);
    User get(String email, String password);
}
