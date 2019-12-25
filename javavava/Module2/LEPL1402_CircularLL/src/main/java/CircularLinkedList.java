import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CircularLinkedList<Item> implements Iterable<Item> {

    private int n;          // size of the list
    private Node last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;

        private Node(Item item){
            this.item = item;
            this.next = null;
        }
    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }


    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Node getLast(){
        return last;
    }

    public Item getItem(Node n){
        return n.item;
    }



    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        Node newLast = new Node(item);

        if (!isEmpty()){
            Node oldLast = getLast();
            newLast.next = oldLast.next;
            oldLast.next = newLast;
            last = newLast;
        }
        else{
            last = newLast;
            last.next = last;
        }
        n++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        if (index < 0 || index > size()-1) {throw new IndexOutOfBoundsException();}

        if (size()==1){
            Item item = last.item;
            last = null;
            n = 0;
            return item;
        } else {
            Node prev = last;
            for (int i = 0; i < index ; i++) {
                prev = prev.next;
            }
            Node removed = prev.next;
            prev.next = removed.next;
            removed.next = null;
            if (index == size() - 1) {
                last = prev;
            }
            n--;
            return removed.item;
        }
    }

    public void print(){
        Iterator<Item> iter = iterator();
        System.out.println();
        System.out.println("CircularLinkedList of size " + size() + " :");

        while (iter.hasNext()){
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Implementation of an iterator that iterates through the items in FIFO order.
     *
     */
    private class ListIterator implements Iterator<Item> {
        private Node current;
        private final int nInit = n;

        ListIterator(){
            current = last;
        }

        private boolean failFastCheck(){
            if (size() != nInit) throw new ConcurrentModificationException("modified while iterating on it");
            return true;
        }

        public boolean hasNext(){
            return (failFastCheck() && current != null);
        }


        public Item next(){
            failFastCheck();
            current = current.next;
            Item item = current.item;
            if (current == last){
                current = null;
            }
            return item;
        }
    }

    public static void main(String[] args){
        CircularLinkedList cll = new CircularLinkedList();
        cll.enqueue("caca");
        cll.enqueue("pipi");
        cll.enqueue("prout");
        cll.print();
        System.out.println("Excepted: caca pipi prout");
        cll.remove(1);
        cll.enqueue("pipi2");
        cll.print();
        System.out.println("Excepted: caca prout pipi2");
    }
}