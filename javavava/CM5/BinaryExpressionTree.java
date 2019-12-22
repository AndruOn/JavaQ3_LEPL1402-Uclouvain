package lepl1402.part4;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BinaryExpressionTree  {

    abstract int size();

    public static BinaryExpressionTree value(int v) {
        return new ValueExpressionTree(v);
    }

    public BinaryExpressionTree plus(BinaryExpressionTree r) {
        return new OperatorExpressionTree('+',this,r);
    }

    public BinaryExpressionTree minus(BinaryExpressionTree r) {
        return new OperatorExpressionTree('-',this,r);
    }

    public BinaryExpressionTree mul(BinaryExpressionTree r) {
        return new OperatorExpressionTree('*',this,r);
    }

    public BinaryExpressionTree div(BinaryExpressionTree r) {
        return new OperatorExpressionTree('/',this,r);
    }

    abstract int evaluate();

    public static BinaryExpressionTree deserialize(String l, int i) {
        if (Character.isDigit(l.charAt(i))) {
            System.out.println("val:"+l.charAt(i));
            return value(Character.getNumericValue(l.charAt(i)));
        } else {
            char op = l.charAt(i);
            System.out.println("op:"+l.charAt(i));
            BinaryExpressionTree right = deserialize(l,i-1);
            BinaryExpressionTree left = deserialize(l,i-1-right.size());
            return new BinaryExpressionTree.OperatorExpressionTree(op,left,right);
        }
    }

    abstract String infixString();
    abstract String postfixString();

    private static class OperatorExpressionTree extends BinaryExpressionTree {
        private final BinaryExpressionTree left;
        private final BinaryExpressionTree right;
        private final char operator;
        private final int size;

        private OperatorExpressionTree(char op, BinaryExpressionTree l, BinaryExpressionTree r) {
            this.operator = op;
            this.left = l;
            this.right = r;
            size = left.size() + right.size() +1;
        }

        @Override
        int size() {
            return size;
        }

        @Override
        int evaluate() {
            int leftRes = left.evaluate();
            int rightRes = right.evaluate();
            switch (operator) {
                case '+':
                    return leftRes + rightRes;
                case '-':
                    return leftRes - rightRes;
                case '/':
                    return leftRes / rightRes;
                case '*':
                    return leftRes * rightRes;
                default:
                    throw new IllegalArgumentException("unkown operator " + operator);
            }
        }

        @Override
        public String infixString() {
            return "("+left.infixString()+" "+operator+" "+right.infixString()+")";
        }

        @Override
        public String postfixString() {
            return left.postfixString()+" "+right.postfixString()+" "+operator;
        }
    }

    private static class ValueExpressionTree extends BinaryExpressionTree {
        private final int value;

        @Override
        int size() { return 1; }

        private ValueExpressionTree(int v) {
            this.value = v;
        }

        @Override
        public String infixString() {
            return value+"";
        }

        @Override
        public String postfixString() {
            return value+"";
        }

        @Override
        int evaluate() {
            return value;
        }
    }

    public static void main(String[] args) {
        BinaryExpressionTree expr1 = value(5).plus(value(7));
        System.out.println(expr1.evaluate()); // 5 + 7

        BinaryExpressionTree expr2 = value(5).plus(value(7)).minus(value(3));
        System.out.println(expr2.evaluate()); // (5+7)-3

        BinaryExpressionTree expr3 = value(2).mul(value(5).plus(value(7)).minus(value(3)));
        System.out.println(expr3.evaluate()); // 2 * ((5+7)-3)

        BinaryExpressionTree expr4 = value(2).mul(value(5).plus(value(7)).minus(value(3)).div(value(3)));
        System.out.println(expr4.evaluate()); // (2 * ((5+7)-3)) / 3
        System.out.println(expr4.infixString());
        System.out.println(expr4.postfixString());


        String postFix = "257+3-3/*";

        int l = postFix.length();

        BinaryExpressionTree expr = deserialize(postFix,postFix.length()-1);
        System.out.println(expr.infixString());





    }
}

