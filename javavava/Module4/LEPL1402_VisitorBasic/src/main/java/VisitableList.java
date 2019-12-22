import java.util.List;

public class VisitableList extends Visitable {

    VisitableList(Object[] list){
        this.elements = list;
    }

    @Override
    void accept(Visitor visitor) {
        for (Object element: this.elements)
            visitor.visit(element);
    }

    public static void main(String[] args) {
        Visitor visitor = new VisitorList(Integer.class);
        Visitable visitable = new VisitableList(new Object[]{1, 2, 3, 3.1, 4, "HELLO"});

        visitor.visit(visitable);
        List<Object> lst = visitor.getFiltered(); // [1, 2, 3, 4]
        System.out.print(lst);
    }
}
