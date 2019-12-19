package ua.polina.collections.arrayList;

import java.util.Iterator;

public class SimpleArrayList<E> implements Array<E> {
    private E[] values;

    public SimpleArrayList() {
        this.values = (E[]) new Object[0];
    }

    public boolean add(E e) {
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[temp.length] = e;
            return true;
        }
        catch(ClassCastException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public void delete(int index) {
        try{
            E[] temp = values;
            values  = (E[]) new Object[temp.length-1];
            System.arraycopy(temp, 0, values, 0, index);
            System.arraycopy(temp, index+1, values, index, temp.length-index-1);
        }
        catch (ClassCastException ex){
            ex.printStackTrace();
        }

    }

    public E get(int index) {
        return values[index];
    }

    public int size() {
        return values.length;
    }

    public void updat(int index, E e) {
        values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(values);
    }
}
