package ua.polina.collections.treeSet;


import lombok.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SimpleTree<E> implements Tree<E> {
    private Leaf<E> root = new Leaf<>(null);
    private int size = 0;

    @Override
    public boolean add(E e) {
        if (size == 0) {
            root.element = e;
            size++;
            return true;
        }
        Leaf<E> newNode = new Leaf<>(e);
        Leaf<E> parentNode = search(root, newNode);
        int compare = parentNode.compareTo(newNode);
        if (compare == 0) return false;
        else if (compare < 0) parentNode.right = newNode;
        else parentNode.left = newNode;
        newNode.parent = parentNode;
        size++;
        return true;
    }

    private Leaf<E> search(final Leaf<E> oldLeaf, final Leaf<E> target) {
        int compare = oldLeaf.compareTo(target);

        if (compare < 0 && oldLeaf.right != null) {
            return search(oldLeaf.right, target);
        } else if (compare > 0 && oldLeaf.left != null) {
            return search(oldLeaf.left, target);
        } else if (compare == 0) {
            return target;
        }

        return oldLeaf;
    }

    @Override
    public List<E> get() {
        List<E> list = new LinkedList<E>();
        for (E e : this) {
            list.add(e);
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Optional<Leaf<E>> find(E e) {
        Leaf<E> result = search(root, new Leaf<>(e));
        if (e.equals(result.element)) {
            return Optional.of(result);
        }
        return Optional.empty();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = 0;
            Iterator<Leaf<E>> iterator = new TreeIterator<>(root);

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                count++;
                return iterator.next().element;
            }
        };
    }

    private class TreeIterator<E> implements Iterator<Leaf<E>> {
        private Leaf<E> next;

        private TreeIterator(Leaf<E> root) {
            next = root;
            goToMostLeftEl();
        }

        private void goToMostLeftEl() {
            while (next.left != null) {
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null && next.element != null;
        }

        @Override
        public Leaf<E> next() {
            Leaf<E> leaf = next;

            if (next.right != null) {
                next = next.right;
                goToMostLeftEl();
            } else {
                goUp();
            }
            return leaf;
        }

        private void goUp() {
            while (true) {
                if (next.parent == null) {
                    next = null;
                    break;
                } else if (next.parent.left == next) {
                    next = next.parent;
                    break;
                }
                next = next.parent;
            }
        }
    }


    @Getter
    public class Leaf<E> implements Comparable<E> {
        private Leaf<E> parent;
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        public Leaf(E element) {
            this.element = element;
        }

        @Override
        public int hashCode() {
            return element.hashCode();
        }

        @Override
        public int compareTo(Object object) {
            Leaf<E> node = (Leaf<E>) object;
            return this.hashCode() - node.hashCode();
        }
    }
}
