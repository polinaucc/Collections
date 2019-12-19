package ua.polina.collections.arrayList;

public interface Array<E> extends Iterable {
    boolean add(E e);
    void delete(int index);
    E get(int index);
    int size();
    void updat(int index, E e);
}
