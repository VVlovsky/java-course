package lab2;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    //...
    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }
    //...

    Matrix(double[][] d) {
        int max_length = 0;
        for (double[] doubles : d) {
            if (doubles.length > max_length) {
                max_length = doubles.length;
            }
        }
        cols = max_length;
        rows = d.length;
        data = new double[rows * cols];

        for (int i = 0; i < d.length; i++) {
            int i_2 = 0;
            for (; i_2 < d[i].length; i_2++) {
                data[i * max_length + i_2] = d[i][i_2];
            }
            for (; i_2 < max_length; i_2++) {
                data[i * max_length + i_2] = 0;
            }
        }


    }


    double[][] asArray() {
        double[][] to_return = new double[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int ii = 0; ii < cols; ii++) {
                to_return[i][ii] = data[index];
                index++;
            }
        }
        return to_return;
    }

    double get(int r, int c) {
        return data[(r - 1) * cols + (c - 1)];
    }

    void set(int r, int c, double value) {
        data[(r - 1) * cols + (c - 1)] = value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; ; i++) {
            buf.append("[");
            for (int i_2 = 0; ; i_2++) {
                buf.append(data[i * cols + i_2]);
                if (i_2 == cols - 1) {
                    break;
                }
                buf.append(", ");
            }
            buf.append("]");
            if (i == rows - 1) {
                break;
            }
            buf.append(", ");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));

        rows = newRows;
        cols = newCols;
    }

    int[] shape() {
        int[] toReturn = new int[2];
        toReturn[0] = cols;
        toReturn[1] = rows;
        return toReturn;
    }

    Matrix add(Matrix m) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = m.data[i] + this.data[i];
        }
        return toReturn;
    }

    Matrix sub(Matrix m) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = m.data[i] - this.data[i];
        }
        return toReturn;
    }

    Matrix mul(Matrix m) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = m.data[i] * this.data[i];
        }
        return toReturn;
    }

    Matrix div(Matrix m) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = m.data[i] / this.data[i];
        }
        return toReturn;
    }

    Matrix add(double w) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = this.data[i] + w;
        }
        return toReturn;
    }

    Matrix sub(double w) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = this.data[i] - w;
        }
        return toReturn;
    }

    Matrix mul(double w) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = this.data[i] * w;
        }
        return toReturn;
    }

    Matrix div(double w) {
        Matrix toReturn = new Matrix(this.rows, this.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = this.data[i] / w;
        }
        return toReturn;
    }

    Matrix dot(Matrix m) {
        Matrix toReturn = new Matrix(this.rows, m.cols);
        for (int i = 0; i < toReturn.data.length; i++) {
            toReturn.data[i] = 0;
            for (int ii = 0; ii < this.cols; ii++) {
                toReturn.data[i] = toReturn.data[i] + this.data[i * this.cols + ii] * m.data[ii * m.cols + i];
            }
        }
        return toReturn;
    }

    Matrix getSubmatrix(int fromRow, int toRow, int fromCol, int toCol) {

        if (toRow >= this.rows || toCol >= this.cols)
            throw new RuntimeException("Error");

        Matrix toReturn = new Matrix(toCol - fromCol, toRow - fromRow);
        int dataI = 0;
        for (int i = fromCol; i < toCol; i++) {
            for (int i_two = fromRow; i_two < toRow; i_two++) {
                toReturn.data[dataI] = this.data[i * this.rows + i_two];
                dataI++;
            }
        }
        return toReturn;
    }
}

