import java.util.function.Predicate;
import java.util.function.Function;

public class Cons<E> {
    // the item inside this list node
    public E v;
    // The next element, null if nothing
    public Cons<E> next;
    // Constructor
    public Cons(E v, Cons<E> next) {
        this.v = v;
        this.next = next;
    }

    public <R> Cons <R> map(Function <E,R> function) {
        if (next != null) {
            return new Cons(function.apply(v), next.map(function));
        } else {
            return new Cons(function.apply(v), null);
        }
    }

    public Cons <E> filter(Predicate <E> predicate) {
        Cons<E> nextThing = null;
        if (next != null) {
            nextThing = next.filter(predicate);
        }
        if (predicate.test(v)) {
            return new Cons(v, nextThing);
        } else {
            return nextThing;
        }
    }
}
