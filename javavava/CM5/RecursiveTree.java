package lepl1402.part4;

import java.util.ArrayList;
import java.util.Iterator;

public class RecursiveTree<A> implements Iterable<A> {

    private final A root;
    private final RecursiveList<RecursiveTree<A>> subForest;

    private RecursiveTree(A root, RecursiveList<RecursiveTree<A>> subForest) {
        this.root = root;
        this.subForest = subForest;
    }

    public static <A> RecursiveTree<A> leaf(A root) {
        return new RecursiveTree(root,RecursiveList.nil());
    }

    public static <A> RecursiveTree<A> node(A root, RecursiveTree<A> ... subForest) {
        return new RecursiveTree<A>(root,RecursiveList.toList(subForest));
    }

    public int preOrderSize() {
        int size = 1;
        for (RecursiveTree st: subForest) {
            size += st.preOrderSize();
        }
        return size;
    }

    public void preOrderCollect(ArrayList<A> elements) {
        elements.add(root);
        for (RecursiveTree st: subForest) {
            st.preOrderCollect(elements);
        }
    }

    public void postOrderCollect(ArrayList<A> elements) {
        for (RecursiveTree st: subForest) {
            st.preOrderCollect(elements);
        }
        elements.add(root);
    }

    @Override
    public Iterator<A> iterator() {
        return null; // TODO
    }

    public static void main(String[] args) {
        RecursiveTree tree = node("H",node("E",leaf("F"),leaf("G")),node("D",leaf("A"),leaf("C")));
        System.out.println(tree.preOrderSize());
        ArrayList<String> res = new ArrayList<>();
        tree.preOrderCollect(res);
        System.out.println(res);

    }
}
