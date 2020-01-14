package lab5;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum() {
    }

    Sum(Node n1, Node n2) {
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n) {
        args.add(n);
        return this;
    }

    Sum add(double c) {
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c, n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result = 0;
        for (Node r : args) {
            result = result + r.evaluate();
        }
        return sign * result;
    }

    int getArgumentsCount() {
        return args.size();
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        if (sign < 0) b.append("-(");
        for (Node a : args) {
            if (a.sign > 0 && args.get(0) != a) b.append(" + ");
            boolean useBracket = false;
            if (a.getSign() < 0) {
                useBracket = true;
                b.append("(-");
            }

            b.append(a.toString());
            if (useBracket) b.append(")");
        }


        if (sign < 0) b.append(")");
        return b.toString();
    }

    Node diff(Variable var) {
        Sum r = new Sum();
        for (Node n : args) {
            r.add(n.diff(var));
        }
        return r;
    }


}