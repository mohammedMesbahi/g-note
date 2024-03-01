package estm.dsic.umi.dao.interfaces;

public interface CrudAble<T, L> {
    T get(L t);

    T create(T t);

    T update(T t);

    T delete(T t);
}
