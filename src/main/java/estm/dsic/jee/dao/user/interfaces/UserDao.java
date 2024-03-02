package estm.dsic.jee.dao.user.interfaces;


import estm.dsic.jee.beans.User;

public interface UserDao extends CrudAble<User, Integer> {
    User get(User user);
    User get(String email, String password);
    User verify(User user);
}
