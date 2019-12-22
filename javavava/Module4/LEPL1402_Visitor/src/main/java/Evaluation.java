public class Evaluation implements Visitor {

    @Override
    public int visit(Add visitable) {
        return visitable.getLeft().accept(this) + visitable.getRight().accept(this);
    }

    @Override
    public int visit(Mult visitable) {
        return visitable.getLeft().accept(this) * visitable.getRight().accept(this);
    }

    @Override
    public int visit(Div visitable) {
        if (visitable.getRight().accept(this) == 0){
            throw new IllegalArgumentException();
        }
        return visitable.getLeft().accept(this) / visitable.getRight().accept(this);
    }

    @Override
    public int visit(Sub visitable) {
        return visitable.getLeft().accept(this) - visitable.getRight().accept(this);
    }

    @Override
    public int visit(Leaf visitable) {
        return visitable.accept(this);
    }

}
