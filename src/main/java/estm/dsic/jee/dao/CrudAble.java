package estm.dsic.jee.dao;

public interface CrudAble<T, L> {
    T get(L t);

    T create(T t);

    T update(T t);

    T delete(T t);
}
