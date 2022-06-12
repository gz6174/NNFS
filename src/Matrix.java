import java.util.Arrays;
import java.util.Random;

public class Matrix {

    private final double[][] M;
    private final int nrow;
    private final int ncol;

    public Matrix(int nrow, int ncol) {
        M = new double[nrow][ncol];
        this.nrow = nrow;
        this.ncol = ncol;
    }

    public Matrix(double[][] N) {
        this.M = N;
        this.nrow = N.length;
        this.ncol = N[0].length;
    }

    public Matrix(int[][] N) {
        this.nrow = N.length;
        this.ncol = N[0].length;
        this.M = new double[this.nrow][this.ncol];
        for (int i = 0; i < this.nrow; i++) {
            for (int j = 0; j < this.ncol; j++) {
                this.M[i][j] = (double) N[i][j];
            }
        }
    }

    public Matrix ones(int nrow, int ncol) {
        Matrix result = new Matrix(nrow, ncol);
        result.fill(1);
        return result;
    }

    public Matrix zeros(int nrow, int ncol) {
        Matrix result = new Matrix(nrow, ncol);
        result.fill(0);
        return result;
    }

    public void fill(double value) {
        for (double[] doubles : M) {
            Arrays.fill(doubles, value);
        }
    }

    public int getNrow() {
        return this.nrow;
    }

    public int getNcol() {
        return this.ncol;
    }

    public double[][] getM() {
        return M;
    }

    public Matrix dot(Matrix N) throws Exception {
        int resNrow = this.nrow;
        int resNcol = N.getNcol();
        if (this.ncol != N.getNrow()) {
            throw new Exception("Dimension mismatch");
        }
        Matrix result = new Matrix(resNrow, resNcol);
        for (int i = 0; i < this.nrow; i++) {
            for (int j = 0; j < N.getNcol(); j++) {
                for (int k = 0; k < this.ncol; k++) {
                    result.getM()[i][j] += M[i][k] * N.getM()[k][j];
                }
            }
        }
        return result;
    }

    public void rand() {
        Random r = new Random();
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                M[i][j] = r.nextDouble();
            }
        }
    }

    public void add() {

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (double[] doubles : M) {
            for (double aDouble : doubles) {
                result.append(aDouble).append(" ");
            }
            result.append('\n');
        }
        return result.toString();
    }

}
