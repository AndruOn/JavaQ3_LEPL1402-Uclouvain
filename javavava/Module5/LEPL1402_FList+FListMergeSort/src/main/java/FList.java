

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FList<A> implements Iterable<A> {
    /**
     * Returns an empty FList
     */
    public static <A> FList<A> nil() {
        return (Nil<A>) Nil.INSTANCE;
    }

    /**
     * Creates a new list with a prepended to this list
     */
    public final FList<A> cons(final A a) {
        return new Cons(a, this);
    }

    /**
     * @return the number of elements in the list
     */
    public abstract int length();

    /**
     * @return true if the list is empty, false otherwise
     */
    public abstract boolean isEmpty();

    /**
     * @return the head of the list.
     * Throws NoSuchElementException if the list is empty
     */
    public abstract A head();

    /**
     * @return the tail of the list (i.e. the sublist without the first element of this list)
     * Throws NoSuchElementException if the list is empty
     */
    public abstract FList<A> tail();

    /**
     * Returns a new list with the output of the function f applied to each element of this list
     */
    public abstract <B> FList<B> map(Function<A,B> f);

    /**
     * Creates a new list with only the element that fullfill the predicate f (i.e. f(elem) == true).
     */
    public abstract FList<A> filter(Predicate<A> f);

    public abstract void print();


    public Iterator<A> iterator() {
        return new Iterator<A>() {

            private FList<A> current = FList.this;

            public boolean hasNext() {
                return !(current.length() == 0);
            }

            public A next() {
                if (hasNext()) {
                    A returnValue = current.head();
                    current = current.tail();
                    return returnValue;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public abstract boolean isNotEmpty();


    static final class Nil<A> extends FList<A> {
        public static final Nil<Object> INSTANCE = new Nil();

        @Override
        public int length() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public A head() {
            throw  new NoSuchElementException();
        }

        @Override
        public FList<A> tail() {
            throw  new NoSuchElementException();
        }

        @Override
        public <B> FList<B> map(Function<A, B> f) {
            return FList.nil();
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            return FList.nil();
        }

        @Override
        public void print() {
            System.out.println(" null]");
        }

        @Override
        public boolean isNotEmpty() {
            return false;
        }
    }

    private static final class Cons<A> extends FList<A> {

        private final A head;
        private final FList<A> tail;

        public Cons(A a, FList<A> list) {
            super();
            this.head = a;
            this.tail = list;
        }

        public static FList<Integer> fList(Integer[] values){
            FList<Integer> newflist = FList.nil();
            for (int i = values.length-1; i >= 1; i--) {
                newflist = newflist.cons(values[i]);
            }
            return new Cons(values[0], newflist);
        }

        @Override
        public int length() {
            int length = 1;
            Cons<A> cur = this;
            while ( !(cur.tail.isEmpty()) ){
                length++;
                cur = (Cons<A>) cur.tail;
            }
            return length;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public A head() {
            return this.head;
        }

        @Override
        public FList<A> tail() {
            return this.tail;
        }

        @Override
        public <B> FList<B> map(Function<A, B> f) {
            return new Cons(f.apply(this.head), this.tail.map(f));
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            if (f.test(head)) {
                return new Cons(head, tail.filter(f));
            } else {
                return tail.filter(f);
            }
        }

        @Override
        public void print() {
            Iterator<A> iter = iterator();
            System.out.print("[");
            while (iter.hasNext()) {
                A schnu = iter.next();
                System.out.print(String.format("%s, ",schnu));
            }
            System.out.println("null]");
        }

        @Override
        public boolean isNotEmpty() {
            return true;
        }
    }

    public static void main(String[] args) {
        Integer[] values = new Integer[]{8, 5, 7, 4, 10, 3};
        FList<Integer> flist = Cons.fList(values);
        flist.print();
        //FList<Object> flist1 = FList.nil().cons(1).cons(2).cons(3).cons(4);
        //flist1.print();
        FListMerge.mergeSort(flist).print();
    }
}