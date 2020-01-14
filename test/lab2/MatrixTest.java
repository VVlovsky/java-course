package lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void constructorTest() {
        Matrix expected = new Matrix(2, 5);
        assertEquals(expected.data.length / -5, -2);
    }

    @Test
    public void constructorTest_2() {
        Matrix expected = new Matrix(new double[][]{{1, 2}, {3, 4}});
        assertArrayEquals(expected.asArray(), new double[][]{{1, 2}, {3, 4}});
    }

    @Test
    public void asArray() {
        double[][] exampleArray = {{1, 2}, {3, 4}};
        Matrix expected = new Matrix(exampleArray);
        double[][] asArrayOutput = expected.asArray();
        for (int i = 0; i < asArrayOutput.length; i++){
            for(int ii = 0; ii < asArrayOutput[i].length; ii++){
                assertEquals(asArrayOutput[i][ii], exampleArray[i][ii], 0.0);
            }
        }
    }

    @Test
    public void get() {
        double[][] exampleArray = {{1, 2}, {3, 4}};
        Matrix expected = new Matrix(exampleArray);
        assertEquals(expected.get(2,1), 3, 0);
    }

    @Test
    public void set() {
        double[][] exampleArray = {{1, 2}, {3, 4}};
        Matrix expected = new Matrix(exampleArray);
        expected.set(2, 1, 324);
        assertEquals(324, expected.get(2, 1), 0);
    }

    @Test
    public void testToString() {


        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }


    }

    @Test (expected = RuntimeException.class)
    public void reshape() {
        double[][] exampleArray = {{1, 2}, {3, 4}};
        Matrix expected = new Matrix(exampleArray);
        expected.reshape(21, 2);
    }

    @Test
    public void shape() {
        double[][] first = {{1, 2}, {3}};
        double[][] second = {{1, 2}, {4}};
        assertArrayEquals(first[0], second[0], .1);
        assertArrayEquals(first[1], second[1], .1);
    }

    @Test
    public void add() {
    }

    @Test
    public void sub() {
        Matrix expected = new Matrix(new double[][]{{1,2},{3,4}});
//        Matrix diff = expected.sub(result);
//        double err = diff.frobenius();
//        assertEquals(err,0,1e-5);
    }

    @Test
    public void mul() {
    }

    @Test
    public void div() {
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testSub() {
    }

    @Test
    public void testMul() {
    }

    @Test
    public void testDiv() {
    }

    @Test
    public void dot() {
    }
}