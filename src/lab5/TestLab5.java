package lab5;

import java.util.Locale;

public class TestLab5 {
    static void buildAndPrint() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1, new Power(x, 3))
                .add(new Power(x, 2))
                .add(-2, x)
                .add(7);
        System.out.println(exp.toString());

    }

    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        System.out.println(exp.toString());
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    static void defineCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .add(8, x)
                .add(4, y)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100 * (Math.random() - .5);
        double yv = 100 * (Math.random() - .5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s", xv, yv, (fv < 0 ? "wewnątrz" : "na zewnątrz"), circle.toString()));
        System.out.println();
        int counter = 0;
        do {
            xv = 100 * (Math.random() - .5);
            yv = 100 * (Math.random() - .5);
            x.setValue(xv);
            y.setValue(yv);
            fv = circle.evaluate();
            if (fv < 0) {
                counter++;
                System.out.print(String.format("Punkt (%f,%f) leży %s koła %s", xv, yv, (fv < 0 ? "wewnątrz" : "na zewnątrz"), circle.toString()));
                System.out.println();
            }
        } while (counter < 100);

    }

    static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2, new Power(x, 3))
                .add(new Power(x, 2))
                .add(-2, x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

    }

    public static void main(String[] args) {
//        buildAndPrint();
//        buildAndEvaluate();
//        defineCircle();
        diffPoly();
    }
}

