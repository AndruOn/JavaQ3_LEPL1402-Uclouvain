import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyArrayList<Item> implements Iterable<Item> {

    private Object [] list;
    private int size;

    //Constructor
    public MyArrayList(int initSize){
        if (initSize < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.size = 0;
        this.list = new Object[initSize];
    }

    /*
    * Checks if 'list' needs to be resized then add the element at the end 
    * of the list.
    */
    public void enqueue(Item item){
        if (size == list.length) {
            Object[] newlist = new Object[size + 10];
            for (int i = 0; i < list.length; i++) {
                newlist[i] = list[i];
            }
            newlist[size++] = item;
            list = newlist;
        } else {
            list[size++] = item;
        }
    }

    /*
    * Removes the element at the specified position in this list.
    * Returns the element that was removed from the list. You dont need to 
    * resize when removing an element.
    */
    public Item remove(int index){
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Item item = (Item) list[index];
        for (int i = 0; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size-- - 1] = null;
        return item;
    }

    public void print(){
        System.out.print(String.format("size: %s ", Integer.toString(size())));
        System.out.println(String.format(" array: %s", Arrays.toString(list)));
    }

    public int size(){ return this.size; }

    public Object [] getList(){ return this.list; }

    @Override
    public Iterator<Item> iterator() { return new MyArrayListIterator(); }


    private class MyArrayListIterator implements Iterator<Item> {

        private int index;
        private final int initSize = size;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (failFastCheck()) {
                if (hasNext()) {
                    return (Item) list[index++];
                }
            }
            return null;
        }

        private boolean failFastCheck(){
            if (initSize == size) {
                return true;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> l = new MyArrayList<>(10);
        l.enqueue(0);
        l.enqueue(1);
        l.enqueue(2);
        l.enqueue(3);
        l.enqueue(4);
        l.enqueue(5);
        l.print();
        l.remove(2);
        l.print();
        Iterator iter = l.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}