import java.util.ArrayList;
import java.util.List;

public class postFixCalculator {
    private List<String> operators = new ArrayList<String>(){{
        add("+"); add("-"); add("/"); add("*");
    }};

    //TO DOOO
    public static Node StringToTree(String s) {

        return new Add(new Leaf(1),new Leaf(1));
    }

    //Applies operator to n1,n2 (ex: n1+n2)
    private static String calculate(String n1, String n2, String operator) {
        switch (operator) {
            case "+":
                return Double.toString(Double.parseDouble(n1) + Double.parseDouble(n2));
            case "-":
                return Double.toString(Double.parseDouble(n1) - Double.parseDouble(n2));
            case "/":
                return Double.toString(Double.parseDouble(n1) / Double.parseDouble(n2));
            case "*":
                return Double.toString(Double.parseDouble(n1) * Double.parseDouble(n2));
            default:
                throw new UnsupportedOperationException();
        }
    }

    public Double postfixCalculator(String s) {
        String[] elements = s.split(" ");
        int nbOfElements = elements.length;

        while (nbOfElements != 1) {
            print(elements);
            int index_operator = 0;
            while (!(operators.contains(elements[index_operator]))) {
                index_operator++;
            }
            print(elements[index_operator]);

            elements[index_operator-2] = calculate(elements[index_operator-2], elements[index_operator-1], elements[index_operator]);
            nbOfElements -= 2;
            System.arraycopy(elements, index_operator+1, elements, index_operator-1, nbOfElements - index_operator+1);
            elements[nbOfElements-1 + 1] = " ";
            elements[nbOfElements-1 + 2] = " ";
        }
        return Double.parseDouble(elements[0]);
    }


    static void print(String s) { System.out.println(s);}

    static void print(String[] s) {
        StringBuilder strB = new StringBuilder();
        strB.append("[");
        for (String str: s) {
            char[] chars = str.toCharArray();
            for (char c : chars) {
                strB.append(c);
            }
            strB.append(", ");
        }
        strB.delete(strB.length()-2,strB.length());
        strB.append("]");
        System.out.println(strB.toString());
    }

    public static void main(String[] args) {
        postFixCalculator c = new postFixCalculator();
        System.out.println(c.postfixCalculator("3 5 + 14 3 / + 23 -"));
    }

}
