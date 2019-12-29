public class Cons {
    // the item inside this list node
    public int v;
    // The next element, null if nothing
    public Cons next;
    // creates a new Cons that applies function f on all elements
    public Cons map(F f) {
        if (next != null) {
            return new Cons(f.apply(v), next.map(f));
        } else {
            return new Cons(f.apply(v), null);
        }
    }
    // creates a new Cons with all elements that matches predicate p
    public Cons filter(P p) {
        if (next == null) {
            if (p.filter(v)) {
                return new Cons(v, null);
            } else {
                return null;
            }
        }else{
            if (p.filter(v)) {
                return new Cons(v, next.filter(p));
            } else {
                return next.filter(p);
            }
        }
    }
    // Constructor
    public Cons(int v, Cons next) {
        this.v = v;
        this.next = next;
    }
}