package estm.dsic.jee.dao.user.interfaces;

import estm.dsic.jee.beans.User;

import java.util.Vector;

public interface CrudAble<T, L> {
    T get(L t);

    T create(T t);

    T update(T t);

    T delete(T t);

    Vector<T> getAll();
}
