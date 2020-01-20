package lab2;

public class TestMatrix {
    public static void main(String[] args) {
//        testMatrix();
//        testSetGet();
//        testString();
        Matrix m = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix col = m.getSubmatrix(1, 3, 1, 3);
        for (double data : col.data) {
            System.out.print(data + "  ");
        }
        System.out.println();
    }

    static void testMatrix() {
        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        for (double data : m.data) {
            System.out.print(data + "  ");
        }
        System.out.println();
    }

    static void testSetGet() {
        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        System.out.println(m.get(3, 2));
        m.set(3, 2, 13);
        System.out.println(m.get(3, 2));
        for (double data : m.data) {
            System.out.print(data + "  ");
        }
        System.out.println();

    }

    static void testString() {
        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        System.out.println(m.toString());
    }


}
