package ua.polina.collections.hashTree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new SimpleTree<>();
        tree.add(1);
        tree.add(4);
        tree.add(-1);
        tree.add(8);
        tree.get().forEach(System.out::println);
        System.out.println(tree.find(-1).get().getElement());
    }
}
