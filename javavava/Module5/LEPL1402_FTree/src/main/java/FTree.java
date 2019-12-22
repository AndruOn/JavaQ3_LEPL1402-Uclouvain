import java.util.function.Function;

public abstract class FTree<A> {

    public final int depth() {
        int depth = 1;
        if (this instanceof Node){
            return depth + this.left().depth();
        }
        return 0;
    }

    public abstract A value();
    public abstract FTree<A> left();
    public abstract FTree<A> right();

    public final <B> FTree<B> map(Function<A,B> f) {
        if (this instanceof Node){
            return new Node(f.apply(value()), left().map(f), right().map(f));
        }else{
            return new Leaf(f.apply(value()));
        }
    }

}
