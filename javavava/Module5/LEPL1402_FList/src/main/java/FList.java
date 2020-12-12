import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class FList<A> implements Iterable<A> {
    /**
     * Returns an empty FList
     */
    public static <A> FList<A> nil() {
        return (Nil<A>) Nil.INSTANCE;
    }

    /**
     * Returns a new FList obtained by prepending a to this list
     */
    public final FList<A> cons(final A a) {
        return new Cons(a, this);
    }

    /**
     * @return the number of elements in this list
     */
    public abstract int length();

    /**
     * @return true if the list is empty, false otherwise
     */
    public abstract boolean isEmpty();

    /**
     * @return the head of the list.
     * @throws NoSuchElementException if the list is empty
     */
    public abstract A head();

    /**
     * @return the tail of the list (i.e. the sublist without the first element of this list)
     * @throws NoSuchElementException if the list is empty
     */
    public abstract FList<A> tail();

    /**
     * Returns a new list containing the outputs obtained by applying function f to each element of this list
     */
    public abstract <B> FList<B> map(Function<A,B> f);

    /**
     * Returns a new list containing only the elements from this list that fullfill predicate f (i.e. f(elem) == true)
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
        int size = 0;

        @Override
        public int length() {
            return size;
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
            return (FList<B>) this;
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            return this;
        }

        @Override
        public void print(String s) { return; }
    }

    private static final class Cons<A> extends FList<A> {
        private int size = 0;
        private A cons ;
        private FList<A> nextCons;

        public Cons(A a, FList<A> as) {
            cons = a;
            if (as == null)
                nextCons = FList.nil();
            else
                nextCons = as;
            size = nextCons.length() + 1;
        }

        @Override
        public int length() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public A head() {
            return cons;
        }

        @Override
        public FList<A> tail() {
            if (size == 0)
                throw new NoSuchElementException();
            return nextCons;
        }

        @Override
        public <B> FList<B> map(Function<A, B> f) {
            return new Cons<B>(f.apply(cons), nextCons.map(f));
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            if (f.test(cons))
                return new Cons<A>(cons, nextCons.filter(f));
            return nextCons.filter(f);
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
    }



    public static void main(String[] args) {

        Cons<Integer> dru = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Nil<>()))));
        Iterator<Integer> iter = dru.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        System.out.println();

        FList<Integer> filteredDru = dru.filter( (i)->(i>2) );
        filteredDru.print("filtered");
        FList<Integer> mapDru = filteredDru.map( (i)->(i-5) );
        mapDru.print("maped");

    }
}