package lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod() {
    }

    Prod(Node n1) {
        args.add(n1);
    }

    Prod(double c) {
        args.add(new Constant(c));
    }

    Prod(Node n1, Node n2) {
        args.add(n1);
        args.add(n2);
    }

    Prod(double c, Node n) {
        this.mul(c);
        this.mul(n);
    }


    Prod mul(Node n) {
        args.add(n);
        return this;
    }

    Prod mul(double c) {
        args.add(new Constant(c));
        return this;
    }


    @Override
    double evaluate() {
        double result = 1;
        for (Node a : args) {
            result = result * a.evaluate();
        }
        return sign * result;
    }

    int getArgumentsCount() {
        return args.size();
    }


    public String toString() {
        StringBuilder b = new StringBuilder();
        if (sign < 0) b.append("-");
        for (int i = 0; i < args.size() - 1; i++) {
            boolean useBrackets = false;
            if (args.get(i).getSign() < 0) {
                useBrackets = true;
                b.append("(");
            }
            b.append(args.get(i).toString());
            if (useBrackets) b.append(")");
            b.append(" * ");
        }
        b.append(args.get(args.size() - 1));
        return b.toString();
    }

    Node diff(Variable var) {
        Sum r = new Sum();
        for (int i = 0; i < args.size(); i++) {
            Prod m = new Prod();
            for (int j = 0; j < args.size(); j++) {
                Node f = args.get(j);
                if (j == i) m.mul(f.diff(var));
                else m.mul(f);
            }
            r.add(m);
        }
        return r;
    }

}