package ua.polina.collections.linkedList;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

public class LinkedContainer<E> implements Linked<E>, Iterable<E>, DescendingIterator<E> {
    public static void main(String... args) {
        LinkedContainer<String> strings = new LinkedContainer<>();
        strings.addLast("one");
        strings.addLast("two");
        strings.addLast("three");

        strings.addFirst("zero");
        strings.addFirst("-one");

        for (String s : strings) System.out.println(s);
        Iterator iterator = strings.descendingIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    public LinkedContainer() {
        this.last = new Node<>(null, null, first);
        this.first = new Node<>(null, last, null);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return getElementByIndex(index++);
            }

            @Override
            public void remove() {

            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            int index = size - 1;

            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public E next() {
                return getElementByIndex(index--);
            }

            @Override
            public void remove() {

            }
        };
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = last;
        newNode.setCurrentElement(e);
        last = new Node<E>(null, null, newNode);
        newNode.setNextElement(last);
        size++;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = first;
        newNode.setCurrentElement(e);
        first = new Node<E>(null, newNode, null);
        newNode.setPrevElement(first);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getElementByIndex(int index) {
        Node<E> target = first.getNextElement();
        for (int i = 0; i < index; i++) {
            target = target.getNextElement();
        }
        return target.getCurrentElement();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class Node<E> {
        private E currentElement;
        private Node<E> nextElement;
        private Node<E> prevElement;

    }

}
