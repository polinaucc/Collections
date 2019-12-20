package ua.polina.collections.treeSet;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new SimpleTree<>();
        tree.add(1);
        tree.add(4);
        tree.add(3);
        tree.add(-1);
        tree.add(0);
        tree.add(8);
        tree.add(-2);
        tree.get().forEach(System.out::println);
    }
}
