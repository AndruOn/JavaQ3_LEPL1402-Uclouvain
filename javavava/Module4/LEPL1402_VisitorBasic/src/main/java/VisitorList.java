import java.util.List;

public class VisitorList extends Visitor {


    public VisitorList(Class cls) {
        super(cls);
    }

    @Override
    List<Object> getFiltered() {
        for (Object o : this.filtered){
            if (!(o.getClass().equals(this.toFilter))) {
                return null;
            }
        }
        return this.filtered;
    }

    @Override
    void visit(Visitable visitable) {
        for (Object element: visitable.elements){
            this.visit(element);
        }

    }

    @Override
    void visit(Object o) {
        if (o.getClass().equals(Object[].class)){
            VisitableList visitableList = new VisitableList((Object[]) o);
            this.visit(visitableList);
        }
        if (o.getClass().equals(this.toFilter)){
            this.filtered.add(o);
        }
    }
}
