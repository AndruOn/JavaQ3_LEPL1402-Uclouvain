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
     * Creates a new list with only the element that fulfill the predicate f (i.e. f(elem) == true).
     */
    public abstract FList<A> filter(Predicate<A> f);

    public abstract void print(String msg);


    public Iterator<A> iterator() {
        return new Iterator<A>() {
            private FList<A> current = FList.this;

            public boolean hasNext() {
                return current.length() != 0;
            }

            public A next() {
                if (hasNext()){
                    A item = current.head();
                    current = current.tail();
                    return item;
                } else{
                    throw new NoSuchElementException();
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    private static final class Nil<A> extends FList<A> {
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
            throw new NoSuchElementException();
        }

        @Override
        public FList<A> tail() {
            throw new NoSuchElementException();
        }

        @Override
        public <B> FList<B> map(Function<A, B> f) {
            return nil();
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            return nil();
        }

        @Override
        public void print(String s) { return; }
    }


    private static final class Cons<A> extends FList<A> {
        private A head;
        private FList tail;

        Cons(A a,FList flist){
            this.head = a;
            this.tail = flist;
        }

        @Override
        public void print(String typeFList) {
            Iterator iter = iterator();
            System.out.print(String.format("Flist %s : ", typeFList));
            while (iter.hasNext()){
                System.out.print(iter.next());
                System.out.print(" ");
            }
            System.out.println();
            System.out.print("Length: ");
            System.out.println(length());
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
            if ( this.head() != null || !(this.tail().isEmpty()) ){
                return false;
            }
            return true;
        }

        @Override
        public A head() {
            return head;
        }

        @Override
        public FList<A> tail() {
            return tail;
        }

        @Override
        public <B> FList<B> map(Function<A, B> f) {
            return new Cons( f.apply(head), tail.map(f) );
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            if (f.test(head)) {
                return new Cons(head, tail.filter(f));
            } else {
                return tail.filter(f);
            }
        }
    }

    public static void main(String[] args) {
        Cons<Integer> dru = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Nil<>()))));
        dru.print("");
        FList<Integer> filteredDru = dru.filter( (i)->(i>2) );
        filteredDru.print("filtered");
        FList<Integer> mapDru = filteredDru.map( (i)->(i-5) );
        mapDru.print("maped");

    }
}